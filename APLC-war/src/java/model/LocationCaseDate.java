/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class LocationCaseDate {

    public int cases;
    public LocalDate date;

    public LocationCaseDate(int cases, LocalDate date) {
        this.cases = cases;
        this.date = date;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LocationCase{"
                + "cases=" + cases
                + ", date=" + date
                + '}';
    }
}
