package egaz.go.tz.assets.service.implementation;

import egaz.go.tz.assets.constants.Status;
import egaz.go.tz.assets.dto.AssetDTO;
import egaz.go.tz.assets.entity.Asset;
import egaz.go.tz.assets.entity.Seller;
import egaz.go.tz.assets.repository.AssetRepo;
import egaz.go.tz.assets.repository.SellerRepo;
import egaz.go.tz.assets.service.AssetService;
import egaz.go.tz.assets.service.AuditLogService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class  Assetimplem  implements AssetService {
    @Autowired
    private  AssetRepo assetRepo;
    @Autowired
    private  SellerRepo sellerRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuditLogService auditLogService;

    @Override
    public List<AssetDTO> listAssets() {
        log.info("All asset collection");
       // Pageable pageable = (Pageable) PageRequest.of(0, 10);
       // return assetRepo.findAll((Sort) pageable);
//        List<AssetDTO> collect = assetRepo.findAll().stream().map(asset ->
//                //modelMapper.map(asset, Asset.class)).collect(Collectors.toList());
//                new AssetDTO(
//                        asset.getId(),
//                        asset.getName(),
//                        asset.getPrice(),
//                        asset.getStatus(),
//                        asset.getSeller().getName()
//
//                )
//        ).collect(Collectors.toList());
        return null; //collect;
    }

    @Override
    public Optional<Asset> getAsset(String barcode) {
        log.info("assets by barcode {}", barcode);
       Optional <Asset> response = assetRepo.findAssetsByBarcode(barcode);
        return response;
    }


    @Override
    public List<Asset> uploadAsset(List<AssetDTO > asset) {
        if (asset.isEmpty()) {
            throw new RuntimeException("Asset list is empty!");
        }


      //  Long sellerId = asset.get(0).getSellerId();
        Seller seller = sellerRepo.findById(asset.getFirst().getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found!"));

        List<Asset> assets = new ArrayList<>();
        for (AssetDTO assetDTO : asset) {
//            Optional<Asset> checkByBarcode = assetRepo.findAssetsByBarcode(assetDTO.getBarcode());
//            if (checkByBarcode.get().getBarcode().equals(assetDTO.getBarcode())) {
//                log.info("barcode {} found", assetDTO.getBarcode());
//            }
                Asset assetToUpdate = modelMapper.map(assetDTO, Asset.class);
            assetToUpdate.setId(null);
            assetToUpdate.setActive(true);
                assetToUpdate.setSeller(seller);
                assetToUpdate.setBarcode(assetDTO.getBarcode());
                assetToUpdate.setName(assetDTO.getName());
                assetToUpdate.setPrice(assetDTO.getPrice());
                assetToUpdate.setStatus(assetDTO.getStatus());
                log.info("pass here {}", assetToUpdate);
                Asset savedasset = assetRepo.save(assetToUpdate);
                log.info("pass again");
                assets.add(savedasset);
                log.info("log error");
//            auditLogService.auditLogs(   "CREATE",
//                    "User",
//                    savedasset.getId().toString(),
//                    "Created new asset: " + seller.getEmail(),
//                    seller.getName() );
            }

        return assets;
    }


    @Override
    public List<Asset> getActiveAsset(boolean active) {
        log.info("get the active assets");
        return assetRepo.findByActive(active);

    }

    @Override
    public Asset saveAsset(AssetDTO request) {
        Seller seller = sellerRepo.findById(request.getSellerId())
                .orElseThrow(() -> new RuntimeException("Seller not found!"));
        Asset asset = modelMapper.map(request, Asset.class);
        asset.setSeller(seller);
        asset.setActive(true);
        return assetRepo.save(asset);
    }

    @Override
    public List<AssetDTO> findAllAssets() {
        List<Asset> assets = assetRepo.findAll();
        return assets.stream().map(a -> modelMapper.map(a, AssetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetDTO> findBySeller_Id(Long sellerId) {
        Seller seller = sellerRepo.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found!"));
          List<Asset> asset= assetRepo.findBySeller_Id(sellerId);
          if (asset.isEmpty() || asset == null){
              log.info("No assets found by seller id {}", sellerId);
              return List.of();
          } else
        return asset.stream().map(a -> modelMapper.map(a, AssetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetDTO> findAssetsByStatus(Status status) {
       List <Asset> asset = assetRepo.findAssetByStatus(status);
        return asset.stream().map(a -> modelMapper.map(a, AssetDTO.class))
                .collect(Collectors.toList());
    }


}


