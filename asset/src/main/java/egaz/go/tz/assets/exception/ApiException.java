package egaz.go.tz.assets.exception;

import lombok.Data;

@Data
public class ApiException {
    private String message;
    private Throwable cause;
    public ApiException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
