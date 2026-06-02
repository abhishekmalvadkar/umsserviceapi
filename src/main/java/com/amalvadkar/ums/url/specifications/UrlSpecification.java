package com.amalvadkar.ums.url.specifications;

import com.amalvadkar.ums.common.model.dto.LoggedInUser;
import com.amalvadkar.ums.url.entities.UrlEntity;
import com.amalvadkar.ums.url.models.request.FetchUrlsRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UrlSpecification {

    public static Specification<UrlEntity> byFilter(FetchUrlsRequest request, LoggedInUser loggedInUser) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("createdBy").get("id"),loggedInUser.userId()));

            if (request.hasUrlStatusId()) {
                predicates.add(cb.equal(root.get("urlStatus").get("id"),request.getUrlStatusId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
