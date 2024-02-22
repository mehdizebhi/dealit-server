package ir.dealit.restful.module.job.service.impl;

import ir.dealit.restful.dto.attachment.Attachment;
import ir.dealit.restful.dto.contract.Milestone;
import ir.dealit.restful.dto.enums.ProposalStatus;
import ir.dealit.restful.dto.proposal.NewProposal;
import ir.dealit.restful.dto.proposal.Proposal;
import ir.dealit.restful.module.attachment.entity.AttachmentEntity;
import ir.dealit.restful.module.attachment.repository.AttachmentRepository;
import ir.dealit.restful.module.contract.entity.MilestoneEntity;
import ir.dealit.restful.module.job.entity.ProposalEntity;
import ir.dealit.restful.module.job.repository.JobAdRepository;
import ir.dealit.restful.module.job.repository.ProposalRepository;
import ir.dealit.restful.module.job.service.ProposalService;
import ir.dealit.restful.module.user.entity.UserEntity;
import ir.dealit.restful.module.user.repository.CustomUserModuleRepository;
import ir.dealit.restful.module.user.repository.UserRepository;
import ir.dealit.restful.util.exception.DealitException;
import ir.dealit.restful.util.exception.JobNotFoundException;
import ir.dealit.restful.util.exception.NotFoundResourceException;
import ir.dealit.restful.util.hateoas.AttachmentModelAssembler;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final AttachmentRepository attachmentRepository;
    private final JobAdRepository jobAdRepository;
    private final CustomUserModuleRepository userRepository;
    private final AttachmentModelAssembler assembler;
    
    @Override
    public Integer countNewProposals(ProposalStatus status, UserEntity user) {
        /*var jobAds = jobAdService.jobAds(JobAdStatus.ACTIVE, user);
        Integer count = 0;
        for (var jobAd : jobAds) {
            count += proposalRepository.countByJobAdAndSeenByClient(jobAd.getId(), false);
        }
        return count;*/
        return 0;
    }

    @Override
    public Integer count(ProposalStatus status, UserEntity owner) {
        return proposalRepository.countByStatusAndOwner(status, owner.getId());
    }

    @Override
    @Transactional
    public ObjectId createNewProposal(NewProposal newProposal, UserEntity user) {
        var jobAdOp = jobAdRepository.findById(new ObjectId(newProposal.jobAdId()));
        if (!jobAdOp.isPresent()) {
            throw new NotFoundResourceException("jobAd is not found");
        }
        int count = proposalRepository.countByOwnerIdAndJobAdId(user.getId(), new ObjectId(newProposal.jobAdId()));
        if (count > 0) {
            throw new DealitException("proposal has already been sent", HttpStatus.NOT_ACCEPTABLE);
        }
        if (user.getConnections() < jobAdOp.get().getRequiredConnection()) {
            throw new DealitException("not enough connections", HttpStatus.NOT_ACCEPTABLE);
        }
        userRepository.updateConnections(user.getId(), user.getConnections() - jobAdOp.get().getRequiredConnection());
        ProposalEntity entity = ProposalEntity.builder()
                .hourlyPriceRate(new BigDecimal(newProposal.hourlyPriceRateSuggestion()))
                .fixedPriceRate(new BigDecimal(newProposal.fixedPriceRateSuggestion()))
                .milestones(newProposal.milestones().stream().map(this::milestoneEntityMapper).collect(Collectors.toList()))
                .suggestProjectLength(newProposal.suggestProjectLength())
                .coverLetter(newProposal.coverLetter())
                .answers(newProposal.answers())
                .owner(user)
                .status(ProposalStatus.ACTIVE)
                .jobAd(jobAdOp.get())
                .seenByClient(false)
                .build();

        List<AttachmentEntity> attachmentEntities = newProposal.files().stream().map(file -> attachmentRepository.findById(new ObjectId(file)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        entity.setAttachment(attachmentEntities);

        entity = proposalRepository.save(entity);
        return entity.getId();
    }

    @Override
    public Page<Proposal> proposals(UserEntity owner, Pageable pageable) {
        return proposalRepository.findByOwner(owner, pageable)
                .map(this::toModel);
    }

    @Override
    public Proposal proposal(ObjectId id, UserEntity owner) {
        var proposalEntityOp = proposalRepository.findByIdAndOwner(id, owner);
        if (!proposalEntityOp.isPresent()) {
            throw new NotFoundResourceException("proposal id is not found");
        }
        return toModel(proposalEntityOp.get());
    }

    @Override
    public List<Attachment> attachmentsOfProposal(ObjectId id, UserEntity user) {
        var proposalOp = proposalRepository.findById(id);
        if (proposalOp.isPresent()) {
            return proposalOp.get().getAttachment().stream().map(assembler::toModel).collect(Collectors.toList());
        }
        throw new NotFoundResourceException("proposal is not found");
    }

    private MilestoneEntity milestoneEntityMapper(Milestone milestone) {
        return MilestoneEntity.builder()
                .step(milestone.step())
                .description(milestone.description())
                .priceRate(new BigDecimal(milestone.ratePrice()))
                .startedAt(milestone.startedAt().toDate())
                .endedAt(milestone.endedAt().toDate())
                .build();
    }

    private Milestone toMilestone(MilestoneEntity milestoneEntity) {
        return Milestone.builder()
                .step(milestoneEntity.getStep())
                .description(milestoneEntity.getDescription())
                .ratePrice(milestoneEntity.getPriceRate().doubleValue())
                .startedAt(new DateTime(milestoneEntity.getStartedAt()))
                .endedAt(new DateTime(milestoneEntity.getEndedAt()))
                .build();
    }

    private Proposal toModel(ProposalEntity entity) {
        Proposal model = new Proposal(
                entity.getId().toString(),
                entity.getHourlyPriceRate().doubleValue(),
                entity.getFixedPriceRate().doubleValue(),
                entity.getMilestones().stream().map(this::toMilestone).collect(Collectors.toList()),
                entity.getSuggestProjectLength(),
                entity.getCoverLetter(),
                entity.getAnswers(),
                entity.getStatus(),
                entity.getJobAd().getId().toString(),
                new DateTime(entity.getCreatedAt()),
                new DateTime(entity.getUpdatedAt()),
                entity.isSeenByClient()
        );

        return model;
    }
}
