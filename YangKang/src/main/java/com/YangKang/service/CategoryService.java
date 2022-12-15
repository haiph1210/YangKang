package com.YangKang.service;

import com.YangKang.entity.Category;
import com.YangKang.form.CategoryCreateForm;
import com.YangKang.form.CategoryFillterForm;
import com.YangKang.form.CategoryUpdateForm;
import com.YangKang.repository.ICategoryRepository;
import com.YangKang.specification.CategorySpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ICategoryRepository repository;

    @Override
    public Page<Category> findAll(Pageable pageable, CategoryFillterForm form){
        Specification<Category> specification = CategorySpecification.buildWhere(form);
        return repository.findAll(specification,pageable);
    }
    @Override
    public Category findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public Category findByName(String name){
        return repository.findByName(name);
    }
    @Override
    public void createCategory(CategoryCreateForm createForm){
        Category category = mapper.map(createForm,Category.class);
        repository.save(category);
    }
    @Override
    public void updateCategory(CategoryUpdateForm updateForm){
        Category category = mapper.map(updateForm,Category.class);
        repository.save(category);
    }
    @Override
    public void delete(Integer id){
        repository.deleteById(id);
    }
    @Override
    public void deleteListId(List<Integer> ids){
        repository.deleteAllById(ids);
    }
}
