package ir.dealit.restful.dto.job;

import ir.dealit.restful.dto.enums.PositionStatus;

public record UpdateJobPosition(
        String title,
        PositionStatus status
) {
}
