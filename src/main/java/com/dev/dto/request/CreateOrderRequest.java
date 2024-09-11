package com.dev.dto.request;

public record CreateOrderRequest (
        Long restaurantId,
        Long addressId

) {
}
