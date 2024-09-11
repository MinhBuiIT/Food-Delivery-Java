package com.dev.repository;

import com.dev.models.Cart;
import com.dev.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.id=:id")
    Optional<Cart> findByIdWithCartItem(Long id);
}
