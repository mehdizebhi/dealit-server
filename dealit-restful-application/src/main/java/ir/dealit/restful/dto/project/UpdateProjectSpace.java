package ir.dealit.restful.dto.project;

import lombok.Builder;
import lombok.Data;

@Builder
public record UpdateProjectSpace(String title) {
}
