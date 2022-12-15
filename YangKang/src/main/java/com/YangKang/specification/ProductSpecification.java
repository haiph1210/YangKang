package com.YangKang.specification;

import com.YangKang.entity.Category;
import com.YangKang.entity.Product;
import com.YangKang.form.ProductFillterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class ProductSpecification {
    public static Specification<Product> buildWhere(ProductFillterForm form){
        if (form == null) {
            return null;
        }
        return hasNamelike(form.getSearch())
                .and(hasRamEqual(form.getRam())
                        .and(hasSalePriceEqual(form.getSalePrice())
                                .and(hasMinPriceGenterThanEqualTo(form.getMinPrice())
                                        .and(hasCreatedDateEqual(form.getCreatedDate())
                                                .and(hasMinCreatedDateGenterThanEqualTo(form.getMinCreatedDate())
                                                        .and(hasMaxCreatedDateLessThanEqualTo(form.getMaxCreatedDate())
                                                                .and(hasYearEqual(form.getYear()))))))));
    }

    private static Specification<Product> hasNamelike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return builder.like(root.get("name"), "%"+ search +"%");
        };
    }

    private static Specification<Product> hasRamEqual(Product.Ram ram){
       return (root, query, builder) -> {
           if (ram == null){
               return null;
           }
           return builder.equal(root.get("ram"),ram);
       };
    }

    private static Specification<Product> hasSalePriceEqual(Double salePrice){
        return (root, query, builder) -> {
            if (salePrice == null){
                return null;
            }
            return builder.equal(root.get("salePrice"),salePrice);
        };
    }

    private static Specification<Product> hasMinPriceGenterThanEqualTo(Double minPrice){
        return (root, query, builder) -> {
            if (minPrice == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("price"),minPrice);
        };
    }

    private static Specification<Product> hasCreatedDateEqual(LocalDate createdDate){
        return  (root, query, builder) -> {
            if (createdDate == null){
                return null;
            }
            return builder.equal(root.get("createdDate").as(LocalDate.class),createdDate);
        };
    }

    private static Specification<Product> hasMinCreatedDateGenterThanEqualTo(LocalDate minCreatedDate){
        return (root, query, builder) -> {
            if (minCreatedDate == null){
                return null;
            }
            return builder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class),minCreatedDate);
        };
    }

    private static Specification<Product> hasMaxCreatedDateLessThanEqualTo(LocalDate maxCreatedDate){
        return (root, query, builder) -> {
            if (maxCreatedDate == null){
                return null;
            }
            return builder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class),maxCreatedDate);
        };
    }

    private static Specification<Product> hasYearEqual(Integer year){
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
