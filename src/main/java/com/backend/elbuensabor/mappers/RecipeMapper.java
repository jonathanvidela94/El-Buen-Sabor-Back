package com.backend.elbuensabor.mappers;

import com.backend.elbuensabor.DTO.RecipeDTO;
import com.backend.elbuensabor.DTO.RecipeDetailDTO;
import com.backend.elbuensabor.entities.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecipeMapper extends GenericMapper<Recipe, RecipeDTO> {

    static RecipeMapper getInstance() {return Mappers.getMapper(RecipeMapper.class);}
}
