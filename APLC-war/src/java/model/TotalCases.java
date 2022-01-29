/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author user
 */
public class TotalCases {

    public Location location;
    public EachTotalCases totalConfirmedCases;
    public EachTotalCases totalDeathCases;
    public EachTotalCases totalRecoveredCases;

    public TotalCases(Location location, EachTotalCases totalConfirmedCases, EachTotalCases totalDeathCases, EachTotalCases totalRecoveredCases) {
        this.location = location;
        this.totalConfirmedCases = totalConfirmedCases;
        this.totalDeathCases = totalDeathCases;
        this.totalRecoveredCases = totalRecoveredCases;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public EachTotalCases getTotalConfirmedCases() {
        return totalConfirmedCases;
    }

    public void setTotalConfirmedCases(EachTotalCases totalConfirmedCases) {
        this.totalConfirmedCases = totalConfirmedCases;
    }

    public EachTotalCases getTotalRecoveredCases() {
        return totalRecoveredCases;
    }

    public void setTotalRecoveredCases(EachTotalCases totalRecoveredCases) {
        this.totalRecoveredCases = totalRecoveredCases;
    }

    public EachTotalCases getTotalDeathCases() {
        return totalDeathCases;
    }

    public void setTotalDeathCases(EachTotalCases totalDeathCases) {
        this.totalDeathCases = totalDeathCases;
    }

    @Override
    public String toString() {
        return "TotalCases{" + "location=" + location + ", totalConfirmedCases=" + totalConfirmedCases + ", totalRecoveredCases=" + totalRecoveredCases + ", totalDeathCases=" + totalDeathCases + '}';
    }

}
