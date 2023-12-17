package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.module.attachment.service.AttachmentService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.user.service.UserService;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AttachmentService attachmentService;
    private final AttachmentModelAssembler assembler;
    private final UserRepository userRepository;

    @Override
    public boolean updateProfilePicture(MultipartFile img, UserEntity user) throws Exception {
        if (img.getContentType().split("/")[0].equals("image")) {
            var attachment = attachmentService.save(assembler.multipartFileToModel(img), true);
            if (attachment.isPresent()) {
                String href = attachment.get().getUri();
                user.setPictureHref(href);
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        }
        // TODO : Throw exception related to illegal file format
        return false;
    }
}
