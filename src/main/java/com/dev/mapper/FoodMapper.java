package com.dev.mapper;

import com.dev.dto.response.CategoryFoodResponse;
import com.dev.dto.response.FoodIngredientResponse;
import com.dev.dto.response.FoodOptimizeResponse;
import com.dev.dto.response.FoodResponse;
import com.dev.models.CategoryFood;
import com.dev.models.Food;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    @Mapping(target = "categoryFood",ignore = true)
    @Mapping(target = "ingredients",ignore = true)
    @Mapping(target = "disabled",ignore = true)
    FoodResponse toFoodResponse(Food food);

    FoodOptimizeResponse toFoodOptimizeResponse(Food food);

    @Mapping(target = "ingredients",ignore = true)
    FoodIngredientResponse toFoodIngredientResponse(Food food);
}
