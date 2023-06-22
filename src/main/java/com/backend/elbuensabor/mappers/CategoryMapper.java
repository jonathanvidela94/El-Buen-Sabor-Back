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
    @Mapping(source = "source.fatherCategory.denomination", target = "categoryFatherDenomination")
    @Mapping(source = "source.itemType.id", target = "itemTypeId")
    CategoryDTO toDTO(Category source);

    @Mapping(target = "fatherCategory", ignore = true)
    @Mapping(target = "childCategories", ignore = true)
    Category toEntity(CategoryDTO source);

}
