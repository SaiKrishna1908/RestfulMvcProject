package com.restful.mvc.services;

import com.restful.mvc.api.v1.Mapper.CategoryMapper;
import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;
import com.restful.mvc.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {
        //given
        List<Category>  categories = Arrays.asList(new Category(), new Category(), new Category());

        //when
        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> res  = categoryService.getAllCategories();

        assertEquals(3,res.size());
    }

    @Test
    void getCategoryByName() {
        //given
        Category category = new Category();
        category.setName("Fruity");

        //when
        when(categoryRepository.findByName(any())).thenReturn(category);

        CategoryDTO res= categoryService.getCategoryByName("Fruity");

        assertEquals("Fruity", res.getName());

    }
}