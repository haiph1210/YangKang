package com.YangKang.specification;

import com.YangKang.entity.Account;
import com.YangKang.entity.Category;
import com.YangKang.form.CategoryFillterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class CategorySpecification {
    public static Specification<Category> buildWhere(CategoryFillterForm form){
        if (form == null) {
            return null;
        }
        return hasNameLike(form.getSearch())
                .and(hasCreatedDateEqual(form.getCreatedDate())
                        .and(hasMinCreatedDateGenterThanEqualTo(form.getMinCreatedDate())
                                .and(hasMaxCreatedDateLessThanEqualTo(form.getMaxCreatedDate())
                                        .and(hasYearEqual(form.getYear())))));
    }

    private static Specification<Category> hasNameLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return builder.like(root.get("name"),"%"+ search +"%");
        };
    }

    private static Specification<Category> hasCreatedDateEqual(LocalDate createdDate){
        return  (root, query, builder) -> {
            if (createdDate == null){
                return null;
            }
            return builder.equal(root.get("createdDate").as(LocalDate.class),createdDate);
        };
    }

    private static Specification<Category> hasMinCreatedDateGenterThanEqualTo(LocalDate minCreatedDate){
        return (root, query, builder) -> {
            if (minCreatedDate == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class),minCreatedDate);
        };
    }

    private static Specification<Category> hasMaxCreatedDateLessThanEqualTo(LocalDate maxCreatedDate){
        return (root, query, builder) -> {
            if (maxCreatedDate == null){
                return null;
            }
            return builder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class),maxCreatedDate);
        };
    }

    private static Specification<Category> hasYearEqual(Integer year){
        return (root, query, builder) -> {
            if (year == null){
                return null;
            }
            return builder.equal(
                    builder.function("YEAR",Integer.class,root.get("createdDate")),year
            );
        };
    }
}
