package egaz.go.tz.assets.controller;

import egaz.go.tz.assets.constants.Status;
import egaz.go.tz.assets.dto.AssetDTO;
import egaz.go.tz.assets.entity.Asset;
import egaz.go.tz.assets.repository.AssetRepo;
import egaz.go.tz.assets.repository.SellerRepo;
import egaz.go.tz.assets.service.AssetService;
import egaz.go.tz.assets.service.SellerService;
import egaz.go.tz.assets.service.implementation.Assetimplem;
import egaz.go.tz.assets.service.implementation.SellerImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/assets")

public class AssetController {
    @Autowired
    private Assetimplem assetService;

    @Autowired
   private ModelMapper modelMapper;



    @GetMapping("/allasset")
    public List<AssetDTO>findAllAssets() {

        return assetService.findAllAssets();
    }
    @PostMapping("/save")
    public ResponseEntity<List<AssetDTO>> addAsset(@RequestBody List<AssetDTO> assetDTO) {


       List <Asset> asset = assetService.uploadAsset(assetDTO);
       // AssetDTO response = modelMapper.map(asset, AssetDTO.class);
        List<AssetDTO> response= asset.stream().map(a -> modelMapper.map(a, AssetDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }


    @GetMapping("/availableassets")
    public ResponseEntity<List<AssetDTO>> getAvailableAssets(@Param("status") String status) {
        List<AssetDTO> availableAssets = assetService.findAssetsByStatus(Status.AVAILABLE);
        List<AssetDTO> response= availableAssets.stream().map(s -> modelMapper.map(s, AssetDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/soldasset")
    public ResponseEntity<List<AssetDTO>> getSoldAssets(@Param("status") String status) {
        List<AssetDTO> soldassets = assetService.findAssetsByStatus(Status.SOLD);
        List<AssetDTO> response= soldassets.stream().map(s -> modelMapper.map(s, AssetDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/activeasset")
    public ResponseEntity<List<Asset>> displayActive() {
        List<Asset> activeAssets = assetService.getActiveAsset(true);
        return ResponseEntity.ok(activeAssets);

    }

    @GetMapping("/bookedasset")
    public ResponseEntity<List<AssetDTO>> getBookedAssets(@Param("status") String status) {
        List<AssetDTO> bookedAssets= assetService.findAssetsByStatus(Status.BOOKED);
        List<AssetDTO> response= bookedAssets.stream().map(s -> modelMapper.map(s, AssetDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/asset/{sellerId}")
   public ResponseEntity<List<AssetDTO>> getAssetBySellerId(@PathVariable("sellerId") Long sellerId) {
       List<AssetDTO> assetDTOS = assetService.findBySeller_Id(Long.valueOf(sellerId));
        return ResponseEntity.ok(assetDTOS);
    }
}



