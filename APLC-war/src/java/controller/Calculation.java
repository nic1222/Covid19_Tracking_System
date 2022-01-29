/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import model.ByPeriodCases;
import model.EachTotalCases;
import model.HighestLowestCases;
import model.Location;
import model.LocationCaseDate;
import model.TotalCases;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author user
 */
public class Calculation {

    static String pathConfirmed = "C:\\Users\\user\\Documents\\NetBeansProjects\\APLC\\APLC-war\\src\\time_series_covid19_confirmed_global.csv";
    static String pathRecovered = "C:\\Users\\user\\Documents\\NetBeansProjects\\APLC\\APLC-war\\src\\time_series_covid19_recovered_global.csv";
    static String pathDeath = "C:\\Users\\user\\Documents\\NetBeansProjects\\APLC\\APLC-war\\src\\time_series_covid19_deaths_global.csv";
    private static List<CSVRecord> csvConfirmedRecords;
    private static List<CSVRecord> csvRecoveredRecords;
    private static List<CSVRecord> csvDeathRecords;

    final static Map<String, TemporalAdjuster> ADJUSTERS = new HashMap<>();

    //Criteria 3: Find the highest/lowest death and recovered Covid-19 cases as per country
    public static List<HighestLowestCases> highestAndLowestCases(List<CSVRecord> csvRecords) {
        List<String> headerDate = getHeaders(csvRecords).subList(4, csvRecords.get(0).size());
        return csvRecords.stream().map(record -> {
            List<LocationCaseDate> loc = headerDate.stream().map(headers -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                LocalDate formattedDate = LocalDate.parse(headers, formatter);
                int cases = Integer.parseInt(record.get(headers));
                return new LocationCaseDate(cases, formattedDate);
            }).collect(Collectors.toList());
            return new HighestLowestCases(
                    new Location(record.get(0), record.get(1), record.get(2), record.get(3)),
                    findHighestCases(loc, loc.size()), findLowestCases(loc, loc.size()));
        }).collect(Collectors.toList());
    }

    static int findHighestCases(List<LocationCaseDate> list, int size) {
        if (size == 1) {
            return list.get(0).getCases();
        }
        return Math.max(minusAndCheck.apply(list.get(size - 1).getCases(), list.get(size - 2).getCases()), findHighestCases(list, size - 1));
    }

    static int findLowestCases(List<LocationCaseDate> list, int size) {
        if (size == 1) {
            return list.get(0).getCases();
        }
        return Math.min(minusAndCheck.apply(list.get(size - 1).getCases(), list.get(size - 2).getCases()), findLowestCases(list, size - 1));
    }

    //Criteria 2: Compute the sum of confirmed cases by week and month for each country
    public static List<ByPeriodCases> sumOfCasesByTime(List<CSVRecord> csvRecords, String time) {
        List<String> headerDate = getHeaders(csvRecords).subList(4, csvRecords.get(0).size());
        return csvRecords.stream().map(record -> {
            List<LocationCaseDate> l = headerDate.stream().map(headers -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                LocalDate formattedDate = LocalDate.parse(headers, formatter);
                int cases = Integer.parseInt(record.get(headers));
                return new LocationCaseDate(cases, formattedDate);
            }).collect(Collectors.toList());
            return new ByPeriodCases(
                    new Location(record.get(0), record.get(1), record.get(2), record.get(3)), getFirstDayAndSumOfCases(l, time));
        }).collect(Collectors.toList());
    }

    public static Map<LocalDate, Integer> getFirstDayAndSumOfCases(List<LocationCaseDate> caseDates, String time) {
        ADJUSTERS.put("week", TemporalAdjusters.previousOrSame(DayOfWeek.of(1)));
        ADJUSTERS.put("month", TemporalAdjusters.firstDayOfMonth());
        Map<LocalDate, List<LocationCaseDate>> result = caseDates.stream()
                .collect(Collectors.groupingBy(cases -> cases.getDate()
                .with(ADJUSTERS.get(time))));
        return result.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> (minusAndCheck.apply(e.getValue().get(e.getValue().size() - 1).getCases(), e.getValue().get(0).getCases()))))
                .entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, TreeMap::new));
    }

    public static BiFunction<Integer, Integer, Integer> minusAndCheck = (x, y) -> x - y < 0 ? 0 : x - y;

    //Criteria 1 & 4
    public static List<EachTotalCases> getConfirmedTotalCases(List<CSVRecord> csvRecords) {
        return csvRecords.stream().map(record
                -> new EachTotalCases(
                        new Location(record.get(0), record.get(1), record.get(2), record.get(3)), record.get(record.size() - 1))
        ).collect(Collectors.toList());
    }

    public static List<EachTotalCases> getDeathTotalCases(List<CSVRecord> csvRecords) {
        return csvRecords.stream().map(record
                -> new EachTotalCases(
                        new Location(record.get(0), record.get(1), record.get(2), record.get(3)), record.get(record.size() - 1))
        ).collect(Collectors.toList());
    }

    public static List<EachTotalCases> getRecoveredTotalCases(List<CSVRecord> csvRecords) {
        return csvRecords.stream().map(record
                -> new EachTotalCases(
                        new Location(record.get(0), record.get(1), record.get(2), record.get(3)), record.get(record.size() - 13))
        ).collect(Collectors.toList());
    }

    public static EachTotalCases getLocation(Location location, List<EachTotalCases> eachTotalCases) {
        return eachTotalCases.stream().filter(cases -> cases.getLocation().equals(location)).findFirst().orElse(null);
    }

    public static List<TotalCases> groupAllCases(List<EachTotalCases> confirmedCases, List<EachTotalCases> deathCases, List<EachTotalCases> recoveredCases) {
        List<Location> locations = confirmedCases.stream().map(EachTotalCases::getLocation).collect(Collectors.toList());
        return locations.stream()
                .map(loc -> new TotalCases(loc, getLocation(loc, confirmedCases), getLocation(loc, deathCases), getLocation(loc, recoveredCases)))
                .collect(Collectors.toList());
    }

    public static List<TotalCases> searchCountryTotalCases(List<TotalCases> totalCases, String s) {
        return totalCases.stream()
                .filter(c -> c.getLocation().getCountry().toLowerCase().startsWith(s.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<ByPeriodCases> searchCountryPeriodCases(List<ByPeriodCases> periodCases, String s) {
        return periodCases.stream()
                .filter(c -> c.getLocation().getCountry().toLowerCase().startsWith(s.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<HighestLowestCases> searchCountryHighestLowest(List<HighestLowestCases> highLowCases, String s) {
        return highLowCases.stream()
                .filter(c -> c.getLocation().getCountry().toLowerCase().startsWith(s.toLowerCase()))
                .collect(Collectors.toList());
    }

    public static List<String> getHeaders(List<CSVRecord> csvRecords) {
        return csvRecords.get(0).getParser().getHeaderNames();
    }

    public static void init() {
        try {
            Reader readerConfirmed = Files.newBufferedReader(Paths.get(pathConfirmed));
            CSVParser csvConfirmedParser = new CSVParser(readerConfirmed, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            csvConfirmedRecords = csvConfirmedParser.getRecords();

            Reader readerRecovered = Files.newBufferedReader(Paths.get(pathRecovered));
            CSVParser csvRecoveredParser = new CSVParser(readerRecovered, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            csvRecoveredRecords = csvRecoveredParser.getRecords();

            Reader readerDeath = Files.newBufferedReader(Paths.get(pathDeath));
            CSVParser csvDeathParser = new CSVParser(readerDeath, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
            csvDeathRecords = csvDeathParser.getRecords();
        } catch (IOException e) {
        }
    }

    public static List<CSVRecord> getCsvConfirmedRecords() {
        return csvConfirmedRecords;
    }

    public static void setCsvConfirmedRecords(List<CSVRecord> csvConfirmedRecords) {
        Calculation.csvConfirmedRecords = csvConfirmedRecords;
    }

    public static List<CSVRecord> getCsvRecoveredRecords() {
        return csvRecoveredRecords;
    }

    public static void setCsvRecoveredRecords(List<CSVRecord> csvRecoveredRecords) {
        Calculation.csvRecoveredRecords = csvRecoveredRecords;
    }

    public static List<CSVRecord> getCsvDeathRecords() {
        return csvDeathRecords;
    }

    public static void setCsvDeathRecords(List<CSVRecord> csvDeathRecords) {
        Calculation.csvDeathRecords = csvDeathRecords;
    }

}

