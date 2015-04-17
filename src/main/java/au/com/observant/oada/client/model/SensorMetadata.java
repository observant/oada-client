/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.model;

import java.util.Collections;
import java.util.Set;

/**
 *
 */
public class SensorMetadata {

    private String id;
    private String portfolio;
    private String name;
    private SensorLocation location;
    private String type;
    private Set<SensorReading> readings = Collections.emptySet();

    // TODO - 7 Apr 2015, lauri - rest of the fields

    /**
     * Returns id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns portfolio.
     *
     * @return the portfolio
     */
    public String getPortfolio() {
        return portfolio;
    }

    /**
     * Sets portfolio.
     *
     * @param portfolio the portfolio to set
     */
    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Returns name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns location.
     *
     * @return the location
     */
    public SensorLocation getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location to set
     */
    public void setLocation(SensorLocation location) {
        this.location = location;
    }

    /**
     * Returns type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns readings.
     *
     * @return the readings
     */
    public Set<SensorReading> getReadings() {
        return readings;
    }

    /**
     * Sets readings.
     *
     * @param readings the readings to set
     */
    public void setReadings(Set<SensorReading> readings) {
        this.readings = readings;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((portfolio == null) ? 0 : portfolio.hashCode());
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
        SensorMetadata other = (SensorMetadata) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (portfolio == null) {
            if (other.portfolio != null) {
                return false;
            }
        } else if (!portfolio.equals(other.portfolio)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SensorMetadata [id=" + id + ", portfolio=" + portfolio + ", name=" + name + "]";
    }
}
