package com.david.springsecrest.repositories;

import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.models.Restaurant;
import com.david.springsecrest.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, UUID> {

    Optional<Restaurant> findRestaurantById(UUID restaurantId);
    Optional<Restaurant> findRestaurantByName(String restaurantName);

    @Query("SELECT r FROM Restaurant r" +
            " WHERE (lower(r.name)  LIKE ('%' || lower(:searchKey) || '%')) " +
            " OR (lower(r.ownerName) LIKE ('%' || lower(:searchKey) || '%')) ")
    Page<Restaurant> searchRestaurant(Pageable pageable, String searchKey);
}
