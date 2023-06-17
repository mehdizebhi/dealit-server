package ir.dealit.restful.hateoas.assembler;

import ir.dealit.restful.controller.v1.user.UserController;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.entity.user.UserEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<UserEntity, UserInfo> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public UserInfoRepresentationModelAssembler() {
        super(UserController.class, UserInfo.class);
    }

    @Override
    public UserInfo toModel(UserEntity entity) {
        return null;
    }

    public List<UserInfo> toListModel(Iterable<UserEntity> entities){
        return null;
    }
}
