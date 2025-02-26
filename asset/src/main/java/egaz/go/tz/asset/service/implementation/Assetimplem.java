package egaz.go.tz.asset.service.implementation;

import egaz.go.tz.asset.entity.Asset;
import egaz.go.tz.asset.repository.AssetRepo;
import egaz.go.tz.asset.service.AssetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class  Assetimplem  implements AssetService {
private final AssetRepo assetRepo ;
    @Override
    public Asset createAsset(Asset asset) {
        log.info("Asset created {}", asset.getBarcode());
        return assetRepo.save(asset);
    }

    @Override
    public Collection<Asset> listAssets() {
     log.info("All asset collection");
        Pageable pageable= (Pageable) PageRequest.of(0, 10);
        return assetRepo.findAll((Sort) pageable);
    }

   @Override
   public Asset getAsset(String barcode) {
      log.info("assets by barcode {}", barcode);
      Asset response=  assetRepo.findAssetsByBarcode(barcode);
      return response;
   }

    @Override
    public Asset updateAsset(Asset asset) {
        log.info("update assets{}", asset.getBarcode());
        return assetRepo.save(asset);
    }




    @Override
    public List<Asset> getActiveAsset(boolean active) {
        log.info("get the active assets" );
        return assetRepo.findByActive(active);

    }

//        public void setActive(boolean active) {
//        this.active = active;
//    }
    }


//    @Override
//    public Asset deleteAsset(String barcode) {
//        log.info("Asset deleted {}", deleteAsset(barcode));
//        assetRepo.(String barcode)



