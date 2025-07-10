package egaz.go.tz.assets.service;

import egaz.go.tz.assets.constants.Status;
import egaz.go.tz.assets.dto.AssetDTO;
import egaz.go.tz.assets.entity.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<AssetDTO>listAssets();
    Optional<Asset> getAsset(String barcode);
    List<Asset> uploadAsset(List<AssetDTO>  asset);
     List<Asset> getActiveAsset(boolean active);
    Asset saveAsset(AssetDTO asset);
    List<AssetDTO>findAllAssets();
    List<AssetDTO>findBySeller_Id(Long seller_id);
    List<AssetDTO>findAssetsByStatus(Status status);

}
