package com.qindel.specifications;

import com.qindel.entities.PriceEntity;
import com.qindel.entities.PriceEntity_;
import com.qindel.requests.FindFinalPriceRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PriceSpecification {

    public static Specification<PriceEntity> finalPrice(@NotNull FindFinalPriceRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PriceEntity_.startDate), request.getDate()));
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PriceEntity_.endDate), request.getDate()));
            }
            if (request.getProductId() != null) {
                predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.productId), request.getProductId()));
            }
            if (request.getBrandId() != null) {
                predicates.add(criteriaBuilder.equal(root.get(PriceEntity_.brandId), request.getBrandId()));
            }
            query.orderBy(criteriaBuilder.desc(root.get(PriceEntity_.priority)), criteriaBuilder.desc(root.get(PriceEntity_.price)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
