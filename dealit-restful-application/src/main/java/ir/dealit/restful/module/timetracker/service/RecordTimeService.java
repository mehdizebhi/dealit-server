package ir.dealit.restful.module.timetracker.service;

import ir.dealit.restful.dto.timetracker.RecordTime;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;

@Secured("ROLE_USER")
public interface RecordTimeService {

    RecordTime recordTime(ObjectId id, UserEntity owner);

    Page<RecordTime> recordTimes(UserEntity owner, Pageable pageable);

    ObjectId register(RecordTime recordTime, UserEntity owner);

    void update(ObjectId id, RecordTime recordTime, UserEntity owner);

    void delete(ObjectId id, UserEntity owner);
}
