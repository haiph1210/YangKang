package com.YangKang.specification;

import com.YangKang.entity.Account;
import com.YangKang.form.AccountFillterForm;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public class AccountSpecification {
    public static Specification<Account> buildWhere(AccountFillterForm form){
        if (form == null){
            return null;
        }
        return hasUsernameLike(form.getSearch())
                .or(hasEmailLike(form.getSearch())
                        .and(hasRoleEqual(form.getRole())
                                .and(hasStatusEqual(form.getStatus())
//                                        .and(hasCreatedDateEqual(form.getCreatedDate())
//                                                .and(hasMinCreatedDateGenterThanEqualTo(form.getMinCreatedDate())
//                                                        .and(hasMaxCreatedDateLessThanEqualTo(form.getMaxCreatedDate())
//                                                                .and(hasYearEqual(form.getYear())))))
                                        )));
    }

    // sắp xếp buildwhere

    private static Specification<Account> hasUsernameLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return builder.like(root.get("username"),"%"+ search +"%");
        };
    }

    private static Specification<Account> hasEmailLike(String search){
        return (root, query, builder) -> {
            if (!StringUtils.hasText(search)){
                return null;
            }
            return builder.like(root.get("email"),"%"+ search +"%");
        };
    }

    private static Specification<Account> hasRoleEqual(Account.Role role){
        return (root, query, builder) -> {
            if (role == null){
                return null;
            }
            return builder.equal(root.get("role"),role);
        };
    }

    private static Specification<Account> hasStatusEqual(Account.Status status){
        return (root, query, builder) -> {
            if (status == null){
                return null;
            }
            return builder.equal(root.get("status"),status);
        };
    }

//    private static Specification<Account> hasCreatedDateEqual(LocalDate createdDate){
//        return  (root, query, builder) -> {
//            if (createdDate == null){
//                return null;
//            }
//            return builder.equal(root.get("createdDate").as(LocalDate.class),createdDate);
//        };
//    }
//
//    private static Specification<Account> hasMinCreatedDateGenterThanEqualTo(LocalDate minCreatedDate){
//        return (root, query, builder) -> {
//            if (minCreatedDate == null){
//                return null;
//            }
//            return builder.greaterThanOrEqualTo(root.get("createdDate").as(LocalDate.class),minCreatedDate);
//        };
//    }
//
//    private static Specification<Account> hasMaxCreatedDateLessThanEqualTo(LocalDate maxCreatedDate){
//        return (root, query, builder) -> {
//            if (maxCreatedDate == null){
//                return null;
//            }
//            return builder.lessThanOrEqualTo(root.get("createdDate").as(LocalDate.class),maxCreatedDate);
//        };
//    }
//
//    private static Specification<Account> hasYearEqual(Integer year){
//        return (root, query, builder) -> {
//            if (year == null){
//                return null;
//            }
//            return builder.equal(
//                    builder.function("YEAR",Integer.class,root.get("createdDate")),year
//                    );
//        };
//    }

}
