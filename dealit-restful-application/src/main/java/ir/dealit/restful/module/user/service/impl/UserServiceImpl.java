package ir.dealit.restful.module.user.service.impl;

import ir.dealit.restful.dto.user.UserActivity;
import ir.dealit.restful.module.attachment.repository.AttachmentRepository;
import ir.dealit.restful.module.attachment.service.AttachmentService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.TokenRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.module.user.service.UserService;
import ir.dealit.restful.util.exception.*;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AttachmentService attachmentService;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentModelAssembler assembler;
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public void updateProfilePicture(MultipartFile img, UserEntity user) throws Exception {
        this.deleteProfilePicture(user);
        if (img.getContentType().split("/")[0].equals("image")) {
            var attachment = attachmentService.save(assembler.multipartFileToModel(img), true);
            if (attachment.isPresent()) {
                String href = attachment.get().getUri();
                user.setPictureHref(href);
                userRepository.save(user);
            }
            return;
        }
        throw new IllegalFileFormatException(HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    @Transactional
    public void deleteProfilePicture(UserEntity user) {
        var attachment = attachmentRepository.findByUri(user.getPictureHref());
        if (attachment.isPresent()) {
            attachmentRepository.deleteById(attachment.get().getId());
            attachmentService.delete(assembler.toModel(attachment.get()));
        }
        user.setPictureHref("https://dealit.s3.ir-thr-at1.arvanstorage.ir/user.png");
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUsername(String username, UserEntity user) {
        int count = userRepository.countByUsername(username);
        if (count > 0) {
            throw new DuplicateUsernameException(HttpStatus.NOT_ACCEPTABLE);
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
            throw new DuplicateEmailException(HttpStatus.NOT_ACCEPTABLE);
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
        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            if (newPassword.equals(confirmNewPassword)) {
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userRepository.save(user);
                return;
            }
            throw new InvalidPasswordException(HttpStatus.NOT_ACCEPTABLE);
        }
        throw new IncorrectPasswordException(HttpStatus.NOT_FOUND);
    }

    @Override
    public Page<UserActivity> activities(UserEntity user, Pageable pageable) {
        List<UserActivity> activities = new ArrayList<>();
        var tokens = tokenRepository.findByUser(user, pageable);
        tokens.forEach(token -> {
            if (token.isExpired()) {
                activities.add(UserActivity.builder()
                        .type("LOGOUT")
                        .title("logout")
                        .description("ip=" + token.getIp())
                        .timestamp(new DateTime(token.getExpiredAt()))
                        .build());
            } else {
                activities.add(UserActivity.builder()
                        .type("LOGIN")
                        .title("login")
                        .description("ip=" + token.getIp())
                        .timestamp(new DateTime(token.getCreatedAt()))
                        .build());
            }
        });
        return new PageImpl<>(activities, pageable, tokens.getTotalElements());
    }
}
