package egaz.go.tz.assets.dto;

import egaz.go.tz.assets.constants.UserRole;
import lombok.Data;

@Data
public class CustomerResponseDTO {
    private Long customer_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private UserRole role;


}
