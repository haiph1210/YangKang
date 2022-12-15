package com.YangKang.service;

import com.YangKang.entity.Category;
import com.YangKang.form.CategoryCreateForm;
import com.YangKang.form.CategoryFillterForm;
import com.YangKang.form.CategoryUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable, CategoryFillterForm form);

    Category findById(Integer id);

    Category findByName(String name);

    void createCategory(CategoryCreateForm createForm);

    void updateCategory(CategoryUpdateForm updateForm);

    void delete(Integer id);

    void deleteListId(List<Integer> ids);
}
