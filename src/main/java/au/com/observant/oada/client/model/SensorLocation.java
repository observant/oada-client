/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.model;

/**
 *
 */
public class SensorLocation {

    private double lon;
    private double lat;
    private double elv;

    /**
     * Returns lon.
     *
     * @return the lon
     */
    public double getLon() {
        return lon;
    }

    /**
     * Sets lon.
     *
     * @param lon the lon to set
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     * Returns lat.
     *
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * Sets lat.
     *
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * Returns elv.
     *
     * @return the elv
     */
    public double getElv() {
        return elv;
    }

    /**
     * Sets elv.
     *
     * @param elv the elv to set
     */
    public void setElv(double elv) {
        this.elv = elv;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(elv);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SensorLocation other = (SensorLocation) obj;
        if (Double.doubleToLongBits(elv) != Double.doubleToLongBits(other.elv)) {
            return false;
        }
        if (Double.doubleToLongBits(lat) != Double.doubleToLongBits(other.lat)) {
            return false;
        }
        if (Double.doubleToLongBits(lon) != Double.doubleToLongBits(other.lon)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SensorLocation [lon=" + lon + ", lat=" + lat + ", elv=" + elv + "]";
    }
}
