package com.YangKang.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "Product")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;
    @Column(name = "name",unique = true,length = 50,nullable = false)
    private String name;
    @Column(name = "sale_price",nullable = true)
    private Double salePrice;
    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "Ram",length = 7,nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Ram ram;
    @Column(name = "amount",nullable = false)
    private Integer amount ;
    @Column(name = "thumbnail_url",nullable = false)
    private String thumbnailUrl;
    @Column(name = "description",length = 500,nullable = false)
    private String description;
    @Column(name = "money",nullable = false)
    private Double money ;
    @Column(name = "created_date",updatable = false,nullable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    @Column(name = "update_at",updatable = false,nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateAt;

    public enum Ram {
        _16GB,_32GB,_64GB,_128GB,_256GB,_1TB
    }


}
