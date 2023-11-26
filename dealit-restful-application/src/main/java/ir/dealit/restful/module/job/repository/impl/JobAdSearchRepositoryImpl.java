package ir.dealit.restful.module.job.repository.impl;

import ir.dealit.restful.dto.job.JobFilter;
import ir.dealit.restful.module.job.repository.JobAdSearchRepository;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import ir.dealit.restful.util.helper.SearchExtractHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JobAdSearchRepositoryImpl implements JobAdSearchRepository {

    private final MongoTemplate template;

    @Override
    public List<JobAdEntity> searchByFilter(JobFilter filter) {
        Query query = internalQuery(filter).with(PageRequest.of(0, 10));
        return template.find(query, JobAdEntity.class);
    }

    @Override
    public List<JobAdEntity> searchByFilter(JobFilter filter, Pageable pageable) {
        Query query = internalQuery(filter).with(pageable);
        return template.find(query, JobAdEntity.class);
    }

    private Query internalQuery(JobFilter filter) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage()
                .matchingAny(SearchExtractHelper.extractSearchKeywords(filter.getSearch()));

        Query query = TextQuery.queryText(criteria).sortByScore();

        if (filter.getMinPrice() != null) {
            query.addCriteria(Criteria.where("minBudget").gte(filter.getMinPrice()));
        }
        if (filter.getMaxPrice() != null) {
            query.addCriteria(Criteria.where("maxBudget").lte(filter.getMaxPrice()));
        }
        if (filter.getProjectLengths() != null && !filter.getProjectLengths().isEmpty()) {
            query.addCriteria(Criteria.where("projectLength").in(filter.getProjectLengths()));
        }
        if (filter.getWeeklyLoads() != null && !filter.getWeeklyLoads().isEmpty()) {
            query.addCriteria(Criteria.where("weeklyLoad").in(filter.getWeeklyLoads()));
        }
        if (filter.getExperienceLevels() != null && !filter.getExperienceLevels().isEmpty()) {
            query.addCriteria(Criteria.where("experienceLevel").in(filter.getExperienceLevels()));
        }
        if (filter.getSubmitRange() != null) {
            // TODO: Calculate submits count
        }

        query.addCriteria(Criteria.where("fixedPrice").in(filter.isFixedPrice()));
        // TODO: isPaymentVerified?
        // TODO: fromPreviousClients

        return query;
    }
}
