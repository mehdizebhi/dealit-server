package ir.dealit.restful.dto.account;

import lombok.Builder;

@Builder
public record FreelancerAccountInfo(
        int contracts,
        int activeContracts,
        int proposals,
        int activeProposal,
        int invitations
) {
}
