package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.RecipeDetailDTO;
import com.backend.elbuensabor.entities.RecipeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeDetailMapper extends GenericMapper<RecipeDetail, RecipeDetailDTO> {

    static RecipeDetailMapper getInstance() {return Mappers.getMapper(RecipeDetailMapper.class);}
}
