package ir.dealit.restful.module.user.service;

import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    boolean updateProfilePicture(MultipartFile img, UserEntity user) throws Exception;
}
