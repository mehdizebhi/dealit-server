package ir.dealit.restful.dto.attachment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment extends RepresentationModel<Attachment> {
        private String id;
        private @JsonIgnore String fileId;
        private String fileName;
        private String fileType;
        private String fileExtension;
        private long fileSize;
        private String href;
        private DateTime createdAt;
        private DateTime updatedAt;
        private String uri;
        private @JsonIgnore byte[] data;
}
