package ir.dealit.restful.dto.account;

import lombok.Builder;

@Builder
public record ClientAccountInfo(
        int projectSpaces,
        int activePositions,
        int contracts,
        int activeContracts,
        int newProposal,
        int activeJobAd,
        double lastMonthOutcome
) {
}
