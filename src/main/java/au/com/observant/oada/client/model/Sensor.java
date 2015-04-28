/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.model;

import java.util.LinkedHashSet;

/**
 *
 */
public class Sensor {

    private String id;
    private String portfolio;
    private LinkedHashSet<SensorReading> readings = new LinkedHashSet<SensorReading>();

    public Sensor() {

    }

    /**
     * @param id
     * @param portfolio
     */
    public Sensor(String id, String portfolio) {
        this.id = id;
        this.portfolio = portfolio;
    }

    /**
     * @param id
     * @param portfolio
     * @param readings
     */
    public Sensor(String id, String portfolio, LinkedHashSet<SensorReading> readings) {
        this.id = id;
        this.portfolio = portfolio;
        this.readings = readings;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    /**
     * Returns id.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
     * Returns readings.
     *
     * @return the readings
     */
    public LinkedHashSet<SensorReading> getReadings() {
        return readings;
    }

    public void setReadings(LinkedHashSet<SensorReading> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "id : " + id + ", portfolio : " + portfolio + ", readings : " + readings;
    }
}
