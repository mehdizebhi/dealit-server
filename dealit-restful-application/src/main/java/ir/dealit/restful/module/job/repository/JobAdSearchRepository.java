package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.module.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobAdSearchRepository {

    Page<JobAdEntity> searchByFilter(JobFilter filter);

    Page<JobAdEntity> searchByFilter(JobFilter filter, Pageable pageable);

    Page<JobAdEntity> searchByFilterAndOwner(JobFilter filter, Pageable pageable, UserEntity owner);
}
