package com.dev.repository;

import com.dev.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {

    Optional<Food> findByName(String name);

    @Query("SELECT f FROM Food f LEFT JOIN f.ingredients i WHERE f.id = :id")
    Optional<Food> findByIdWithIngredients(long id);

    @Query("SELECT f FROM Food f LEFT JOIN f.restaurant i WHERE f.id = :id")
    Optional<Food> findByIdWithRestaurant(long id);
}
