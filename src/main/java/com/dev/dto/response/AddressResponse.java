package com.dev.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressResponse(
        Long id,

        String numberStreet,

        String street,

        String ward,

        String district,

        String city,

        String postalCode
) {
}
