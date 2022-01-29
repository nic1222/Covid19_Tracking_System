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
public class HighestLowestCases {

    public Location location;
    public int highestCase;
    public int lowestCase;

    public HighestLowestCases(Location location, int highestCase, int lowestCase) {
        this.location = location;
        this.highestCase = highestCase;
        this.lowestCase = lowestCase;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getHighestCase() {
        return highestCase;
    }

    public void setHighestCase(int highestCase) {
        this.highestCase = highestCase;
    }

    public int getLowestCase() {
        return lowestCase;
    }

    public void setLowestCase(int lowestCase) {
        this.lowestCase = lowestCase;
    }

    @Override
    public String toString() {
        return "HighestLowestCases{"
                + "location=" + location
                + ", highestCase=" + highestCase
                + ", lowestCase=" + lowestCase
                + '}';
    }
}
