package com.YangKang.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "username",unique = true,length = 50)
    private String username;
    @Column(name = "password",length = 150,nullable = false)
    private String password;
    @Column(name = "email",length = 50,nullable = false)
    private String email;
    @Column(name = "first_name",length = 50,nullable = false)
    private String firstName;
    @Column(name = "last_name",length = 50,nullable = false)
    private String lastName;
    @Formula("concat(first_name , ' ' , last_name)")
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "role",nullable = false,length = 8)
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Column(name = "status",nullable = false,length = 11)
    @Enumerated(value = EnumType.ORDINAL)
    private Status status;
    @Column(name = "avatar_url",length = 250,nullable = true)
    private String avatarurl;
//    @Column(name = "created_date",updatable = false,nullable = false)
//    @CreationTimestamp
//    private LocalDate createdDate;
//    @Column(name = "update_at",updatable = false,nullable = false)
//    @UpdateTimestamp
//    private LocalDateTime updateAt;

    public enum Role{
        ADMIN,MANAGER,EMPOYEE
    }
    public enum Status {
        NOT_ACTIVE, ACTIVE;
    }
}
