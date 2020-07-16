package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
