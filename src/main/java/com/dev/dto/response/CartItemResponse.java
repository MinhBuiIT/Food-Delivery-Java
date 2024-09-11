package com.dev.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CartItemResponse {
    Long id;
    Integer quantity;
    FoodOptimizeResponse food;
    String specialInstructions;
    Long totalPrice;
    List<IngredientItemResponse> ingredients;
}
