package com.YangKang.repository;

import com.YangKang.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account,Integer>, JpaSpecificationExecutor<Account> {
    Account findByUsername(String username);
    Account findByEmail(String email);
    @Modifying
    @Transactional
//    @Query("UPDATE Account ac SET ac.password = :newPassword,ac.status = 0 WHERE id = :id")
    @Query("UPDATE Account ac SET ac.password = :newPassword, ac.status = 0 WHERE ac.id = :id")
    void changePassword(@Param("id") Integer id,@Param("newPassword") String newPassword);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
