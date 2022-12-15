package com.YangKang.form;

import com.YangKang.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateForm {
    private Integer id;
    private String name;
    private Double salePrice;
    private Double price;
    private Product.Ram ram;
    private Integer amount;
    private String thumbnailUrl;
    private String description;
}
