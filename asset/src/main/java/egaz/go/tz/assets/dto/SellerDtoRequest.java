package egaz.go.tz.assets.dto;

import egaz.go.tz.assets.constants.UserRole;
import lombok.Data;

@Data
public class SellerDtoRequest {
    private String name;
    private String email;
    private String phone;
    private UserRole role;
    private String password;
    public SellerDtoRequest() {

    }
}
