package ir.dealit.restful.module.job.repository;

import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobAdSearchRepository {
    List<JobAdEntity> searchByFilter(JobFilter filter);
    List<JobAdEntity> searchByFilter(JobFilter filter, Pageable pageable);
}
