package ir.dealit.restful.dto.proposal;

import ir.dealit.restful.dto.contract.Milestone;
import ir.dealit.restful.dto.enums.ProjectLength;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record NewProposal(
        String jobAdId,
        ProjectLength suggestProjectLength,
        List<Milestone> milestones,
        Double hourlyPriceRateSuggestion,
        Double fixedPriceRateSuggestion,
        String coverLetter,
        Map<String, String> answers,
        List<String> files,
        boolean fixedPriceJob
) {
}