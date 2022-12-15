package com.YangKang.controller;

import com.YangKang.dto.CategoryDTO;
import com.YangKang.entity.Category;
import com.YangKang.form.CategoryCreateForm;
import com.YangKang.form.CategoryFillterForm;
import com.YangKang.form.CategoryUpdateForm;
import com.YangKang.repository.ICategoryRepository;
import com.YangKang.service.ICategoryService;
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
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ICategoryService service;
    @Autowired
    private JSONObject message;
    @GetMapping()
    public Page<CategoryDTO> findAll(Pageable pageable, CategoryFillterForm form){
        Page<Category> page = service.findAll(pageable,form);
        List<Category> categories = page.getContent();
        List<CategoryDTO> dtos = mapper.map(categories,new TypeToken<List<CategoryDTO>>(){}.getType());
        return new PageImpl<>(dtos,pageable,page.getTotalPages());
    }
    @GetMapping("/id/{id}")
    public CategoryDTO findById(@PathVariable Integer id){
        Category category = service.findById(id);
        CategoryDTO categoryDTO = mapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }
    @GetMapping("/name/{name}")
    public CategoryDTO findByName(@PathVariable String name){
        Category category = service.findByName(name);
        CategoryDTO categoryDTO = mapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }
    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateForm createForm){
        service.createCategory(createForm);
        message.put("ResultText","Category Insert Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id,@RequestBody CategoryUpdateForm updateForm){
        updateForm.setId(id);
        service.updateCategory(updateForm);
        message.put("ResultText","Category Update Successfully!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        message.put("ResultText","Delete success!!!");
        message.put("status",200);
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
    @DeleteMapping("/deletelist")
    public ResponseEntity<?> deleteListId(@RequestBody List<Integer> ids){
        service.deleteListId(ids);
        message.put("ResultText","Delete success!!!");
        message.put("status",200);
       return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
}
