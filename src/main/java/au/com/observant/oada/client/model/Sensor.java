/*
 * Copyright (c) 2003-2015 by Observant Pty. Ltd. All rights reserved.
 * Redistribution or reproduction of part or all of the contents of
 * this file in any form is prohibited.
 */
package au.com.observant.oada.client.model;

/**
 *
 */
public class Sensor {

    private final String id;
    private final String portfolio;
    private final String name;

    /**
     * @param id
     * @param portfolio
     * @param name
     */
    public Sensor(String id, String portfolio, String name) {
        super();
        this.id = id;
        this.portfolio = portfolio;
        this.name = name;
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
     * Returns name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Sensor other = (Sensor) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
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
        return "Sensor [id=" + id + ", portfolio=" + portfolio + ", name=" + name + "]";
    }
}
