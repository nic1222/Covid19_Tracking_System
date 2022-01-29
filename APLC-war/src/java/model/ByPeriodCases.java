/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author user
 */
public class ByPeriodCases {

    public Location location;
    public Map<LocalDate, Integer> sumOfCasesByPeriod;

    public ByPeriodCases(Location location, Map<LocalDate, Integer> sumOfCasesByPeriod) {
        this.location = location;
        this.sumOfCasesByPeriod = sumOfCasesByPeriod;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Map<LocalDate, Integer> getSumOfCasesByPeriod() {
        return sumOfCasesByPeriod;
    }

    public void setSumOfCasesByPeriod(Map<LocalDate, Integer> sumOfCasesByPeriod) {
        this.sumOfCasesByPeriod = sumOfCasesByPeriod;
    }

    @Override
    public String toString() {
        return "ByPeriodCases{"
                + "location=" + location
                + ", sumOfCasesByPeriod=" + sumOfCasesByPeriod
                + '}';
    }
}
