package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.CategoryDTO;
import com.backend.elbuensabor.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends GenericMapper<Category, CategoryDTO> {

    static CategoryMapper getInstance() {
        return Mappers.getMapper(CategoryMapper.class);
    }

    @Mapping(source = "source.fatherCategory.id", target = "categoryFatherId")
    CategoryDTO toDTO(Category source);

    @Mapping(target = "fatherCategory", ignore = true)
    @Mapping(target = "childCategories", ignore = true)
    Category toEntity(CategoryDTO source);

}
