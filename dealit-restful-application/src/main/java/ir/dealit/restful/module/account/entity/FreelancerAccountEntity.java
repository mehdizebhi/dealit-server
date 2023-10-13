package ir.dealit.restful.module.account.entity;

import ir.dealit.restful.module.job.entity.JobEntity;
import ir.dealit.restful.module.project.entity.ProjectEntity;
import ir.dealit.restful.module.job.entity.ProposalEntity;
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
@TypeAlias("FreelancerAccount")
public class FreelancerAccountEntity extends AccountEntity {

    private @DocumentReference List<JobEntity> activeJobs;
    private @DocumentReference List<ProposalEntity> proposals;
    private @DocumentReference List<ProjectEntity> projects;

    /*public FreelancerAccountEntity(
            UserEntity user,
            String number,
            WalletEntity wallet,
            InboxEntity inbox,
            ChatEntity chat,
            List<JobEntity> activeJobs,
            List<ProposalEntity> proposals,
            List<ProjectEntity> projects
    ) {
        super(user, number, wallet, inbox, chat);
        this.activeJobs = activeJobs;
        this.proposals = proposals;
        this.projects = projects;
    }*/
}