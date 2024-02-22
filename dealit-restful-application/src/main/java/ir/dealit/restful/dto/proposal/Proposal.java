package ir.dealit.restful.dto.proposal;

import ir.dealit.restful.dto.contract.Milestone;
import ir.dealit.restful.dto.enums.ProjectLength;
import ir.dealit.restful.dto.enums.ProposalStatus;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;

@Builder
public record Proposal(
        String id,
        double hourlyPriceRate,
        double fixedPriceRate,
        List<Milestone> milestones,
        ProjectLength suggestProjectLength,
        String coverLetter,
        Map<String, String> answers,
        ProposalStatus status,
        String jobAdId,
        DateTime createdAt,
        DateTime updatedAt,
        boolean seenByClient
) {
}
