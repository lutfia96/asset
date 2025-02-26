package egaz.go.tz.asset.controller;

import egaz.go.tz.asset.constants.Status;
import egaz.go.tz.asset.entity.Asset;
import egaz.go.tz.asset.repository.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/assets")

public class AssetController {
    @Autowired
    AssetRepo assetRepo;

    @GetMapping("/allasset")
    public ResponseEntity<List<Asset>> displayallasset(@Param("status") String status) {
        return ResponseEntity.ok(assetRepo.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        Asset response = assetRepo.save(asset);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paidassets")
    public ResponseEntity<List<Asset>> getPaidAssets(@Param("status") String status) {
        List<Asset> activeAssets = assetRepo.findAssetByStatus(Status.AVAILABLE);
        return ResponseEntity.ok(activeAssets);

    }

    @GetMapping("/soldasset")
    public ResponseEntity<List<Asset>> getSoldAssets(@Param("status") String status) {
        List<Asset> activeassets = assetRepo.findAssetByStatus(Status.SOLD);
        return ResponseEntity.ok(activeassets);

    }

    @GetMapping("/activeasset")
    public ResponseEntity<List<Asset>> displayActive() {
        List<Asset> activeAssets = assetRepo.findByActive(true);
        return ResponseEntity.ok(activeAssets);

    }

    @GetMapping("/bookedasset")
    public ResponseEntity<List<Asset>> getBrokenAssets(@Param("status") String status) {
        List<Asset> brokenAsset = assetRepo.findAssetByStatus(Status.BOOKED);
        return ResponseEntity.ok(brokenAsset);
    }
}



