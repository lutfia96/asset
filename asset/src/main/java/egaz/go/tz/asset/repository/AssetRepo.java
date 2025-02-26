package egaz.go.tz.asset.repository;

import egaz.go.tz.asset.constants.Status;
import egaz.go.tz.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssetRepo extends JpaRepository<Asset, Long> {


  List<Asset> findAssetByActive(boolean active);

    //Asset findAssetByBarcode(String barcode); // Ensures only one result

    //void deleteByBarcode(String barcode); // Standard Spring Data JPA delete method

   // List<Asset> findAssetsByStatus(Status status); // Fixed method name for clarity

    List<Asset> findAssetByStatus(Status status);

    Asset findAssetsByBarcode(String barcode);

    List<Asset> findByActive(boolean active);
    List<Asset>findAll();
}

