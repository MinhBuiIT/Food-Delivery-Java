package com.dev.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientItemRestaurantResponse {
    Long id;
    String name;
    List<IngredientItemResponse> ingredients;
}
