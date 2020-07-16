package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.CategoryMapper;
import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;
import com.restful.mvc.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = categoryRepository.findAll().stream().map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return categories;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
