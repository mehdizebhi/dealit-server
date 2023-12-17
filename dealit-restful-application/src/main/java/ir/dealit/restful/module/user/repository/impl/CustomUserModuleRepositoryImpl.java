package ir.dealit.restful.module.user.repository.impl;

import ir.dealit.restful.module.user.repository.CustomUserModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomUserModuleRepositoryImpl implements CustomUserModuleRepository {

    private final MongoTemplate mongoTemplate;

}
