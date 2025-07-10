package egaz.go.tz.assets.constants;

public enum BookingStatus {
    BOOKED ("BOOKED"), AVAILABLE ("AVAILABLE");
private String value;
    BookingStatus(String value) {
        this.value= value;

    }
    public String getValue() {
        return value;
    }
}
