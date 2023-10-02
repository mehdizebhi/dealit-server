package ir.dealit.restful.repository;

import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.repository.entity.JobAdEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobAdSearchRepository {
    List<JobAdEntity> searchByFilter(JobFilter filter);
    List<JobAdEntity> searchByFilter(JobFilter filter, Pageable pageable);
}
