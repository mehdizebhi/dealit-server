package ir.dealit.restful.module.project.controller;

import ir.dealit.restful.api.command.CommandProjectSpaceApi;
import ir.dealit.restful.dto.project.ChangeProjectSpace;
import ir.dealit.restful.dto.project.NewProjectSpace;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandProjectSpaceController implements CommandProjectSpaceApi {
    @Override
    public ResponseEntity<Void> createProject(NewProjectSpace newProjectSpace, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateProject(ChangeProjectSpace changeProjectSpace, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProject(ObjectId id, Authentication authentication) {
        return null;
    }
}
