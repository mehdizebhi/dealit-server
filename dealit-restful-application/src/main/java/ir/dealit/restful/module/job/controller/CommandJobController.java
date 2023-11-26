package ir.dealit.restful.module.job.controller;

import ir.dealit.restful.api.command.CommandJobApi;
import ir.dealit.restful.dto.job.ChangeJobPosition;
import ir.dealit.restful.dto.job.NewJobPosition;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandJobController implements CommandJobApi {
    @Override
    public ResponseEntity<Void> createJobPosition(NewJobPosition newJobPosition, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateJobPosition(ChangeJobPosition changeJobPosition, Authentication authentication) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteJobPosition(ObjectId id, Authentication authentication) {
        return null;
    }
}
