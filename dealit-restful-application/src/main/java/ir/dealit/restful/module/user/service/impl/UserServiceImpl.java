package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.module.attachment.service.AttachmentService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.user.service.UserService;
import ir.dealit.restful.util.exception.UserFoundExeption;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AttachmentService attachmentService;
    private final AttachmentModelAssembler assembler;
    private final UserRepository userRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void updateUsername(String username, UserEntity user) {
        int count = userRepository.countByUsername(username);
        if (count > 0) {
            throw new UserFoundExeption("username is exist. you can not change your username;");
        }
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateDisplayName(String displayName, UserEntity user) {
        user.setDisplayName(displayName);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateEmail(String email, UserEntity user) {
        int count = userRepository.countByEmail(email);
        if (count > 0) {
            throw new UserFoundExeption("email is exist. you can not change your email;");
        }
        user.setEmail(email);
        user.setEmailConfirmed(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(String phoneNumber, UserEntity user) {
        user.setPhoneNumber(phoneNumber);
        user.setPhoneConfirmed(false);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(String oldPassword, String newPassword, String confirmNewPassword, UserEntity user) {

    }

    @Override
    @Transactional
    public void resetPassword(String resetToken, String newPassword, String confirmNewPassword, UserEntity user) {

    }
}
