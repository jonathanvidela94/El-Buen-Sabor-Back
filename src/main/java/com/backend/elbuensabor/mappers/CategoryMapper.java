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

    @Override
    @Mapping(source = "denomination", target = "denomination")
    @Mapping(source = "isBanned", target = "isBanned")
    @Mapping(source = "fatherCategory.id", target = "categoryFatherId")
    CategoryDTO toDTO(Category source);

    @Override
    @Mapping(source = "denomination", target = "denomination")
    @Mapping(source = "isBanned", target = "isBanned")
    @Mapping(source = "categoryFatherId", target = "fatherCategory.id")
    Category toEntity(CategoryDTO source);

}
