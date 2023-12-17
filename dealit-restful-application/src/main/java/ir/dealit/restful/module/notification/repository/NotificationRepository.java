package ir.dealit.restful.module.notification.repository;

import ir.dealit.restful.dto.enums.NotificationType;
import ir.dealit.restful.module.notification.entity.NotificationEntity;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<NotificationEntity, ObjectId> {

    @Query(value = "{'type': ?0, 'inbox': ?1}")
    Page<NotificationEntity> findAllByTypeAndInbox(NotificationType type, ObjectId inboxId, Pageable pageable);
}