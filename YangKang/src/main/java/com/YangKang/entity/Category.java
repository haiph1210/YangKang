package com.YangKang.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "Category")
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "name",unique = true,nullable = false,length = 50)
    private String name;
    @Column(name = "amount",nullable = false)
    private Integer amount;
    @Column(name = "created_date",updatable = false,nullable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    @Column(name = "update_at",updatable = false,nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
