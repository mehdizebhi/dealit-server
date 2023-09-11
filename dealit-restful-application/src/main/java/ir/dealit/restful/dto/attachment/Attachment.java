package ir.dealit.restful.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment extends RepresentationModel<Attachment> {
    private String fileId;
    private String fileName;
    private String fileType;
    private String fileExtension;
    private long fileSize;
    private String uri;
    private byte[] data;
}
