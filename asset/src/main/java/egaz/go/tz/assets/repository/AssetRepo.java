package egaz.go.tz.assets.repository;

import egaz.go.tz.assets.constants.Status;
import egaz.go.tz.assets.entity.Asset;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Long> {


  List<Asset> findAssetByActive(boolean active);

  List<Asset> findBySeller_Id(Long seller_id);


  List<Asset> findAssetByStatus(Status status);

  Optional<Asset> findAssetsByBarcode(String barcode);

  List<Asset> findByActive(boolean active);


}

