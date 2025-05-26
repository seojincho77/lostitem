package com.example.lostitem.repositories;

import org.springframework.data.jpa.domain.Specification;
import com.example.lostitem.models.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class PostSpecification {
    public static Specification<Post> filterPosts(
            DateType date,
            CategoryType category,
            String keyword,
            String place,
            PostType type,
            StatusType status
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (type != null) {
                predicates.add(cb.equal(root.get("postType"), type));
            }

            if (status != null && status != StatusType.NONE) {
                predicates.add(cb.equal(root.get("isRecovered"), status == StatusType.DONE));
            }

            if (keyword != null && !keyword.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"));
            }

            if (type==PostType.lost){
                if (place != null && !place.isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("lostItem").get("lostPlace")), "%" + place.toLowerCase() + "%"));
                }
            } else if (type==PostType.found){
                if (place != null && !place.isEmpty()) {
                    predicates.add(cb.like(cb.lower(root.get("foundPlace")), "%" + place.toLowerCase() + "%"));
                }
            }


            if (category != null && category != CategoryType.NONE) {
                predicates.add(cb.equal(root.get("lostItem").get("category"), category));
            }

            if (date != null && date != DateType.NONE) {
                LocalDate now = LocalDate.now();
                LocalDate startDate = switch (date) {
                    case DAY -> now;
                    case WEEK -> now.minusDays(7);
                    case MONTH -> now.minusMonths(1);
                    case YEAR -> now.minusYears(1);
                    default -> null;
                };
                if (startDate != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("lostItem").get("lostDate"), startDate));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
