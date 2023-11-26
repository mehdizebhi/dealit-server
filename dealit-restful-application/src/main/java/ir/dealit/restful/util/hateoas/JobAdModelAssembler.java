package ir.dealit.restful.util.hateoas;

import ir.dealit.restful.module.job.controller.CommandJobAdController;
import ir.dealit.restful.dto.job.JobAd;
import ir.dealit.restful.module.job.entity.JobAdEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JobAdModelAssembler {

}
/*public class JobAdModelAssembler extends RepresentationModelAssemblerSupport<JobAdEntity, JobAd> {

    public JobAdModelAssembler() {
        super(CommandJobAdController.class, JobAd.class);
    }

    @Override
    public JobAd toModel(JobAdEntity entity) {
        if (Objects.nonNull(entity)) {
            JobAd model = createModelWithId(entity.getId(), entity);
            BeanUtils.copyProperties(entity, model);
            model.setId(entity.getId().toString());
            model.setCreatedAt(entity.getCreatedAt().toString());
            model.setUpdatedAt(entity.getUpdatedAt().toString());
            model.setOwnerId(entity.getOwner().getId().toString());
//            model.add(getLinks(entity));
            return model;
        }
        return null;
    }

    private List<Link> getLinks(JobAdEntity entity) {
        List<Link> links = new ArrayList<>();
        return links;
    }
}*/
