package com.YangKang.service;

import com.YangKang.entity.Category;
import com.YangKang.entity.Product;
import com.YangKang.form.ProductCreateForm;
import com.YangKang.form.ProductFillterForm;
import com.YangKang.form.ProductUpdateForm;
import com.YangKang.repository.ICategoryRepository;
import com.YangKang.repository.IProductRepository;
import com.YangKang.specification.ProductSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IProductRepository repository;

    @Override
    public Page<Product> findAll(Pageable pageable, ProductFillterForm form){
        Specification<Product> specification = ProductSpecification.buildWhere(form);
        return repository.findAll(specification,pageable);
    }
    @Override
    public Product findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public Product findByName(String name){
        return repository.findByName(name);
    }
    @Override
    public void createProduct(ProductCreateForm createForm){
        Product product = mapper.map(createForm,Product.class);
        Double money = ((double)product.getAmount()) * product.getPrice() - (product.getPrice() * (product.getSalePrice()/100));
        product.setMoney(money);

        repository.save(product);
    }
    @Override
    public void updateProduct(ProductUpdateForm updateForm){
        Product product = mapper.map(updateForm,Product.class);
        Double money = ((double)product.getAmount()) * product.getPrice() - (product.getPrice() * (product.getSalePrice()/100));
        product.setMoney(money);
        repository.save(product);
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
