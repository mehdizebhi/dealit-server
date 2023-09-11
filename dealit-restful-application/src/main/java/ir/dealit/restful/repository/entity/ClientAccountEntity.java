package ir.dealit.restful.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "accounts")
@TypeAlias("ClientAccount")
public class ClientAccountEntity extends AccountEntity{

    @DocumentReference
    private List<JobEntity> jobs;

    @DocumentReference
    private List<JobAdEntity> ads;

}
