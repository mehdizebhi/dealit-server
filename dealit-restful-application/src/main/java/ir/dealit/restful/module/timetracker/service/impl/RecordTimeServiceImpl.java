package ir.dealit.restful.module.timetracker.service.impl;

import ir.dealit.restful.dto.timetracker.RecordTime;
import ir.dealit.restful.module.contract.repository.ContractRepository;
import ir.dealit.restful.module.timetracker.entity.WorkTimeEntity;
import ir.dealit.restful.module.timetracker.repository.WorkTimeRepository;
import ir.dealit.restful.module.timetracker.service.RecordTimeService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordTimeServiceImpl implements RecordTimeService {

    private final WorkTimeRepository timeRepository;
    private final ContractRepository contractRepository;

    @Override
    public RecordTime recordTime(ObjectId id, UserEntity owner) {
        var entityOp = timeRepository.findByIdAndOwner(id, owner);
        if (!entityOp.isPresent()) {
            throw new NotFoundResourceException("Record time id is not found");
        }
        return toRecordTime(entityOp.get());
    }

    @Override
    public Page<RecordTime> recordTimes(UserEntity owner, Pageable pageable) {
        return timeRepository.findByOwner(owner, pageable)
                .map(this::toRecordTime);
    }

    @Override
    public ObjectId register(RecordTime recordTime, UserEntity owner) {
        var entity = WorkTimeEntity.builder().build();
        BeanUtils.copyProperties(recordTime, entity);
        entity.setContract(contractRepository.findByIdAndHired(recordTime.contractId(), owner).orElseThrow(() -> new NotFoundResourceException("Contract id is not found")));
        entity.setOwner(owner);
        entity = timeRepository.save(entity);
        return entity.getId();
    }

    @Override
    public void update(ObjectId id, RecordTime recordTime, UserEntity owner) {
        var entityOp = timeRepository.findByIdAndOwner(id, owner);
        if (!entityOp.isPresent()){
            throw new NotFoundResourceException("Time id is not found");
        }
        var entity = entityOp.get();
        entity.setTitle(recordTime.title());
        entity.setDescription(recordTime.description());
        entity.setStart(recordTime.start().toDate());
        entity.setEnd(recordTime.end().toDate());
    }

    @Override
    public void delete(ObjectId id, UserEntity owner) {
        var entityOp = timeRepository.findByIdAndOwner(id, owner);
        if (!entityOp.isPresent()){
            throw new NotFoundResourceException("Time id is not found");
        }
        timeRepository.deleteById(id);
    }

    private RecordTime toRecordTime(WorkTimeEntity entity) {
        return RecordTime.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .contractId(entity.getContract().getId())
                .start(new DateTime(entity.getStart()))
                .end(new DateTime(entity.getEnd()))
                .build();
    }
}
