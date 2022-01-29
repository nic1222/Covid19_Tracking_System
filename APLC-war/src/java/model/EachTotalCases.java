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
public class EachTotalCases {

    public Location location;
    public String cases;

    public EachTotalCases(Location location, String totalCases) {
        this.location = location;
        this.cases = totalCases;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {
        return "EachTotalCases{" + "location=" + location + ", totalCases=" + cases + '}';
    }

}
