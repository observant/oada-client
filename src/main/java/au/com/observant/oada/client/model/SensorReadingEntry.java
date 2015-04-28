package au.com.observant.oada.client.model;

public class SensorReadingEntry {

    private String timestamp;
    private String value;

    public SensorReadingEntry() {

    }

    public SensorReadingEntry(String timestamp, String value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "timestamp : " + timestamp + ", value : " + value;
    }
}
