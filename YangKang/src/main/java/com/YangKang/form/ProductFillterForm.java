package com.YangKang.form;

import com.YangKang.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class ProductFillterForm {
    private String search;
    private Product.Ram ram;
    private Double salePrice;
    private Double minPrice;
    private LocalDate createdDate;
    private LocalDate minCreatedDate;
    private LocalDate maxCreatedDate;
    private Integer year;
}
