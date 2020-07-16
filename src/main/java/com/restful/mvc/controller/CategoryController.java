package com.restful.mvc.controller;

import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.api.v1.model.CategoryListDTO;
import com.restful.mvc.domain.Category;
import com.restful.mvc.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    ResponseEntity<CategoryListDTO> getAllCategories(){
        CategoryListDTO categoryListDTO = new CategoryListDTO(categoryService.getAllCategories());

        return new ResponseEntity<CategoryListDTO>(categoryListDTO, HttpStatus.OK);
    }

    @GetMapping("{name}")
    ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }
}
