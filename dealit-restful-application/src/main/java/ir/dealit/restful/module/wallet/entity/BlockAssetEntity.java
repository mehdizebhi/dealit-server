package ir.dealit.restful.module.wallet.entity;

import ir.dealit.restful.module.contract.entity.ContractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockAssetEntity {

    private AssetEntity asset;
    private String reason;
    private ContractEntity contract;
}
