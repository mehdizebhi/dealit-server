package ir.dealit.restful.dto.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MileStone {
    private int step;
    private int days;
    private Double price;
    private String description;
}
