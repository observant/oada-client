package au.com.observant.oada.client.model;

public class SensorReadingEntry {

    private String timestamp;
    private Number value;

    public SensorReadingEntry() {

    }

    public SensorReadingEntry(String timestamp, Number value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "timestamp : " + timestamp + ", value : " + value;
    }
}
