package com.restful.mvc.api.v1.Mapper;

import com.restful.mvc.api.v1.model.CategoryDTO;
import com.restful.mvc.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryDTOMapper {

    CategoryDTOMapper INSTANCE = Mappers.getMapper(CategoryDTOMapper.class);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

}
