package com.YangKang.dto;

import com.YangKang.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private Integer id;
    private String name;
    private Double salePrice;
    private Double price;
    private Product.Ram ram;
    private Integer amount;
    private String thumbnailUrl;
    private String description;
    private Double money ;
    private LocalDate createdDate;
    private LocalDateTime updateAt;

}
