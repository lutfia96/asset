package egaz.go.tz.asset.constants;

import jakarta.persistence.Enumerated;


public enum Status {
    SOLD("SOLD"), BOOKED("BOOKED"), AVAILABLE("AVAILABLE");
    private final String status;
    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return this.status;
    }
}
