package ir.dealit.restful.util.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.Optional;

public class ControllerResponseHelper {

    public static <T> EntityModel<T> toEntityModel(T model) {
        return EntityModel.of(model);
    }

    public static <T> PagedModel<T> toPagedModel(Page<T> page) {
        return PagedModel.of(page.getContent(), new PagedModel.PageMetadata(
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages()
        ));
    }
}
