package ir.dealit.restful.module.job.service;

import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.module.account.repository.AccountRepository;
import ir.dealit.restful.module.job.repository.JobAdRepository;
import ir.dealit.restful.module.job.repository.JobAdSearchRepository;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobAdDaoService {

    private final JobAdRepository repository;
    private final JobAdSearchRepository searchRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public Optional<JobAdEntity> register(JobAd jobAd) {
        return Optional.of(repository.save(toEntity(jobAd)));
    }

    @Transactional
    public void deleteById(ObjectId id) {
        repository.deleteById(id);
    }

    @Transactional
    public Optional<JobAdEntity> update(JobAd jobAd) {
        try {
            var oldEntity = repository.findById(new ObjectId(jobAd.getId()));
            if (oldEntity.isPresent()){
                var newEntity = oldEntity.get();
                BeanUtils.copyProperties(jobAd, newEntity);
                return Optional.of(repository.save(newEntity));
            }
            return Optional.empty();
        } catch (Exception e){
            return Optional.empty();
        }
    }

    public Optional<JobAdEntity> findById(ObjectId id){
        return repository.findById(id);
    }

    public Page<JobAdEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<JobAdEntity> findByFilter(JobFilter filter, Pageable pageable){
        return searchRepository.searchByFilter(filter, pageable);
    }

    private JobAdEntity toEntity(JobAd jobAd) {
        var entity = new JobAdEntity();
        BeanUtils.copyProperties(jobAd, entity);
        entity.setOwner(accountRepository.findById(new ObjectId(jobAd.getOwnerId())).get());
        return entity;
    }
}
