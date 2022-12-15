package com.YangKang.service;

import com.YangKang.entity.Product;
import com.YangKang.form.ProductCreateForm;
import com.YangKang.form.ProductFillterForm;
import com.YangKang.form.ProductUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Pageable pageable, ProductFillterForm form);

    Product findById(Integer id);

    Product findByName(String name);

    void createProduct(ProductCreateForm createForm);

    void updateProduct(ProductUpdateForm updateForm);

    void delete(Integer id);

    void deleteListId(List<Integer> ids);
}
