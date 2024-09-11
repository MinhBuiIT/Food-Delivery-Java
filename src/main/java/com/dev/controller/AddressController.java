package com.dev.controller;

import com.dev.core.ResponseSuccess;
import com.dev.dto.request.CreateAddressRequest;
import com.dev.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseSuccess addAddress(
            @RequestBody CreateAddressRequest request
    ) {
       var result = addressService.createAddress(request);
       return ResponseSuccess.builder()
               .message("Add address for user successful")
               .code(HttpStatus.CREATED.value())
               .metadata(result)
               .build();
    }

    @GetMapping("/me")
    public ResponseSuccess getAddressMe() {
        var result = addressService.getAllAddresses();
        return ResponseSuccess.builder()
                .message("Get addresses successful")
                .code(HttpStatus.OK.value())
                .metadata(result)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess deleteAddress(@PathVariable Long id) {
        addressService.removeAddress(id);
        return ResponseSuccess.builder()
                .message("Delete address successful")
                .code(HttpStatus.OK.value())
                .build();
    }
}
