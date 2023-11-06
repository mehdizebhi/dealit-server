package ir.dealit.restful.util.hateoas.assembler;

import ir.dealit.restful.module.attachment.controller.AttachmentController;
import ir.dealit.restful.api.AttachmentApi;
import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AttachmentModelAssembler extends RepresentationModelAssemblerSupport<AttachmentEntity, Attachment> {

    public AttachmentModelAssembler() {
        super(AttachmentController.class, Attachment.class);
    }

    @Override
    public Attachment toModel(AttachmentEntity entity) {
        if (Objects.nonNull(entity)) {
            Attachment model = createModelWithId(entity.getId(), entity);
            BeanUtils.copyProperties(entity, model);
            model.setId(entity.getId().toString());
            model.add(getLinks(entity));
            return model;
        }
        return null;
    }

    public Attachment multipartFileToModel (MultipartFile file) throws IOException {
        return Attachment.builder()
                .fileId(UUID.randomUUID().toString())
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileExtension(file.getContentType().split("/")[1])
                .fileSize(file.getSize())
                .data(file.getBytes())
                .build();
    }

    private List<Link> getLinks(AttachmentEntity entity) {
        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(AttachmentApi.class).download(entity.getId())).withRel("download"));
        return links;
    }
}
