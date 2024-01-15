package ir.dealit.restful.dto.contract;

import ir.dealit.restful.dto.enums.Currency;
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class Contract {

    private String id;
    private String title;
    private String workroomId;
    private String hired;
    private String hiredBy;
    private ObjectId jobPositionId;
    private boolean fixedPrice;
    private Double budget;
    private Currency currency;
    private long start;
    private long end;
    private long createdAt;
    private long updatedAt;
}
