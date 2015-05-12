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
public class SensorReading {

    private String id;
    private LinkedHashSet<SensorReadingEntry> entries = new LinkedHashSet<SensorReadingEntry>();
    private String errorCode;
    private String errorReason;

    public SensorReading(String id, LinkedHashSet<SensorReadingEntry> readingEntries) {
        this.id = id;
        this.entries = readingEntries;
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
     * Sets id.
     *
     * @param id the id to set
     */

    public void setId(String id) {
        this.id = id;
    }

    public LinkedHashSet<SensorReadingEntry> getEntries() {
        return entries;
    }

    public void setEntries(LinkedHashSet<SensorReadingEntry> entries) {
        this.entries = entries;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public SensorReading() {

    }


    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        SensorReading other = (SensorReading) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id : " + id + ", entries : \n" + entries + "\nerrorCode : " + errorCode + "\nerrorReason : " + errorReason;
    }
}
