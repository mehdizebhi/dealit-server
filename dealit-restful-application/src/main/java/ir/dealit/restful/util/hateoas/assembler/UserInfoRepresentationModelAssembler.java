package ir.dealit.restful.util.hateoas.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ir.dealit.restful.controller.v1.UserController;
import ir.dealit.restful.dto.user.UserInfo;
import ir.dealit.restful.repository.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserInfoRepresentationModelAssembler extends
        RepresentationModelAssemblerSupport<UserEntity, UserInfo> {

    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     */
    public UserInfoRepresentationModelAssembler() {
        super(UserController.class, UserInfo.class);
    }

    @Override
    public UserInfo toModel(UserEntity entity) {
        if (Objects.nonNull(entity)) {
            UserInfo model = createModelWithId(entity.getId(), entity);
            BeanUtils.copyProperties(entity, model);
            model.add(
                    linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel());
            return model;
        }
        return null;
    }

    public List<UserInfo> toListModel(Iterable<UserEntity> entities){
        return null;
    }
}
