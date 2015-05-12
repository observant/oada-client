package au.com.observant.oada.client.model;

import java.math.BigDecimal;

public class SensorReadingEntry {

    private String timestamp;

    private Double value;

    public SensorReadingEntry() {

    }

    public SensorReadingEntry(String timestamp, Double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "timestamp : " + timestamp + ", value : " + value;
    }
}
