package ir.dealit.restful.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableInfo {
    private int size;
    private int page;
    private String[] sort;
    private Sort.Direction direction = Sort.Direction.ASC;
}
