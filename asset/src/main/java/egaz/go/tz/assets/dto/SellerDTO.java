package egaz.go.tz.assets.dto;

import lombok.Data;

import java.util.List;

@Data
public class SellerDTO {
    private String name;
    private String email;
    private String phone;

    private List<AssetDTO> assetDTO;

}
//    public SellerDTO() {
//    }



//    public Asset[] getAssets() {
//        return null;
//    }

