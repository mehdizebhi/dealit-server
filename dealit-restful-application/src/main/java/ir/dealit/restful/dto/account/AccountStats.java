package ir.dealit.restful.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record AccountStats(
        double balance,
        int newMessage,
        int newTransaction,
        int activeProposals,
        int activeJobs,
        int newInvitations,
        int newProposals,
        int activeAds,
        double income,
        double outcome) {
}