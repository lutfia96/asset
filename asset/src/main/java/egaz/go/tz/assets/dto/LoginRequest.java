package egaz.go.tz.assets.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Long sellerId;
}
