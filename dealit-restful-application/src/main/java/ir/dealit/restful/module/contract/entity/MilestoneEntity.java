package ir.dealit.restful.module.contract.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MilestoneEntity {

    private int step;
    private BigDecimal priceRate;
    private String description;
    private Date startedAt;
    private Date endedAt;
}
