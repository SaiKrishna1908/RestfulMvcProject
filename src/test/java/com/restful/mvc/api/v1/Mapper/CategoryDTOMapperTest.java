package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class CategoryDTOMapperTest {

    private static final String NAME = "sloppy";
    private static final Long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryMapper(){

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals("sloppy",categoryDTO.getName());
        assertEquals(ID, Long.valueOf(categoryDTO.getId()));
    }


}