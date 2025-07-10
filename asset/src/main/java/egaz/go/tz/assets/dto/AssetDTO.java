package egaz.go.tz.assets.dto;

import egaz.go.tz.assets.constants.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AssetDTO {
   // private Long id;
    private String name;
    @NotEmpty(message = "this barcode is unique and not empty")
    private String barcode;
    private double price;
    private Status status ;
    private Long sellerId;

    public AssetDTO()
    {

    }


}
