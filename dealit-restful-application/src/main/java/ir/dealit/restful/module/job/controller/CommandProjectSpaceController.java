package ir.dealit.restful.module.project.controller;

import ir.dealit.restful.api.command.CommandProjectSpaceApi;
import ir.dealit.restful.dto.common.ResponseModel;
import ir.dealit.restful.dto.project.UpdateProjectSpace;
import ir.dealit.restful.dto.project.NewProjectSpace;
import ir.dealit.restful.module.project.service.ProjectSpaceService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommandProjectSpaceController implements CommandProjectSpaceApi {

    private final ProjectSpaceService projectSpaceService;

    @Override
    public ResponseEntity<ResponseModel<Map<String, String>>> createProject(NewProjectSpace newProjectSpace, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> updateProject(ObjectId spaceId, UpdateProjectSpace projectSpace, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseModel<Void>> deleteProject(ObjectId spaceId, Authentication authentication) {
        return null;
    }
}
