package com.dev.service;


import com.dev.dto.request.CreateAddressRequest;
import com.dev.dto.response.AddressResponse;
import com.dev.enums.ErrorEnum;
import com.dev.exception.AppException;
import com.dev.mapper.AddressMapper;
import com.dev.models.Address;
import com.dev.models.User;
import com.dev.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AddressService {

    AddressMapper addressMapper;
    UserRepository userRepository;

    @Transactional
    @PreAuthorize("hasRole('USER')")
    public AddressResponse createAddress(CreateAddressRequest request) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmailWithAddress(email)
                .orElseThrow(() -> new AppException(ErrorEnum.NOT_FOUND_USER));

        //Kiểm tra address đã tồn tại chưa
        for (Address address1: user.getAddresses()) {
            if(address1.getCity().equals(request.city()) &&
               address1.getDistrict().equals(request.district()) &&
               address1.getWard().equals(request.ward()) &&
               address1.getNumberStreet().equals(request.numberStreet())) {
                   throw new AppException(ErrorEnum.ADDRESS_EXIST);
            }
        }


        Address address = addressMapper.toAddress(request);
        user.addAddress(address);
        userRepository.save(user);
        return addressMapper.toAddressResponse(address);
    }

    @PreAuthorize("hasRole('USER')")
    public List<AddressResponse> getAllAddresses() {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmailWithAddress(email)
                .orElseThrow(() -> new AppException(ErrorEnum.NOT_FOUND_USER));

        List<AddressResponse> addresses = new ArrayList<>();
        for (Address address : user.getAddresses()) {
            addresses.add(addressMapper.toAddressResponse(address));
        }
        return addresses;
    }

    @Transactional
    @PreAuthorize("hasRole('USER')")
    public void removeAddress(Long id) {
        var email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmailWithAddress(email)
                .orElseThrow(() -> new AppException(ErrorEnum.NOT_FOUND_USER));
        Address address = user.getAddresses().stream()
                .filter(address1 -> address1.getId().equals(id)).findFirst()
                .orElseThrow(() -> new AppException(ErrorEnum.ADDRESS_NOT_FOUND));
        user.removeAddress(address);
        userRepository.save(user);
    }
}
