/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Calculation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author user
 */
public class WritePrologFile {

    public void consultAndWrite() {
        List<EachTotalCases> casesList = Calculation.getConfirmedTotalCases(Calculation.getCsvConfirmedRecords());
        File prologFilePath = new File("C:\\Users\\user\\Documents\\Prolog\\sorting.pl");
        List<String> knowledgeBasedRecord = casesList.stream().map(totalCases -> {
            Location location = totalCases.getLocation();
            String country = location.getCountry()
                    .toLowerCase().replaceAll(",", "").replaceAll(" |'", "_");
            String prov = location.getState()
                    .trim().toLowerCase().replaceAll(",", "").replaceAll(" ", "_");
            country = country.endsWith("*") ? country.substring(0, country.length() - 1) : country;
            return "cases(" + country
                    + (prov.isEmpty() ? "" : "-" + prov)
                    + "," + totalCases.getCases()+ ").\n";
        }).collect(Collectors.toList());
        try {
            FileUtils.writeStringToFile(prologFilePath, String.join("", knowledgeBasedRecord));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
