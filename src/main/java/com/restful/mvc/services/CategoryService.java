package com.restful.mvc.services;

import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;

import java.util.List;

public interface CategoryService  {

    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
