package com.dev.dto.response;

import com.dev.models.Address;
import com.dev.models.Restaurant;

import java.util.Set;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        Set<Address> addresses
) {
}
