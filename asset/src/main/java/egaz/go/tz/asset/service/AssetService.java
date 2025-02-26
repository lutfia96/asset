package egaz.go.tz.asset.service;

import egaz.go.tz.asset.entity.Asset;

import java.util.Collection;
import java.util.List;

public interface AssetService {
    Asset createAsset(Asset asset);
    Collection<Asset>listAssets();
    Asset getAsset(String barcode);
    Asset updateAsset(Asset asset);
     List<Asset> getActiveAsset(boolean active);

//    default Asset deleteAsset(String barcode) {
//        return null;
//    }
}
