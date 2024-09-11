package com.dev.dto.response;

import java.util.Set;


public record FoodOptimizeResponse (
        Long id,
        String name,
        String price,
        String description,
        Set<String> images,
        Boolean available
){
}
