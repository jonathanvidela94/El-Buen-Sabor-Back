package com.backend.elbuensabor.mappers;


import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<Category, CategoryDTO> {

    static CategoryMapper getInstance() {
        return Mappers.getMapper(CategoryMapper.class);
    }

}
