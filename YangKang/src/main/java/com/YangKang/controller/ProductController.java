package com.YangKang.controller;

import com.YangKang.dto.ProductDTO;
import com.YangKang.entity.Product;
import com.YangKang.form.ProductCreateForm;
import com.YangKang.form.ProductFillterForm;
import com.YangKang.form.ProductUpdateForm;
import com.YangKang.repository.IProductRepository;
import com.YangKang.service.IProductService;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/products")

public class ProductController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private IProductService service;
    @Autowired
    private JSONObject message;
    @GetMapping()
    public Page<ProductDTO> findAll(Pageable pageable, ProductFillterForm form){
        Page<Product> page = service.findAll(pageable, form);
        List<Product> products = page.getContent();
        List<ProductDTO> dtos = mapper.map(products,new TypeToken<List<ProductDTO>>(){}.getType());
        return new PageImpl<>(dtos,pageable,page.getTotalPages());
    }
    @GetMapping("/id/{id}")
    public ProductDTO findById(@PathVariable Integer id){
        Product product = service.findById(id);
        ProductDTO productDTO = mapper.map(product,ProductDTO.class);
        return productDTO;
    }
    @GetMapping("/name/{name}")
    public ProductDTO findByName(@PathVariable String name){
        Product product = service.findByName(name);
        ProductDTO productDTO = mapper.map(product,ProductDTO.class);
        return productDTO;
    }
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateForm createForm){
        service.createProduct(createForm);
        message.put("resultText","Product Insert Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,@RequestBody ProductUpdateForm updateForm){
        updateForm.setId(id);
        service.updateProduct(updateForm);
        message.put("resultText","Product update Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        message.put("resultText","Product delete Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());

    }
    @DeleteMapping("/deletelist")
    public ResponseEntity<?> deleteListId(@RequestBody List<Integer> ids){
        service.deleteListId(ids);
        message.put("resultText","Products delete Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
}
