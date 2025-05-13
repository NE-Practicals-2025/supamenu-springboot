package com.david.springsecrest.services;


import com.david.springsecrest.enums.ERestaurantType;
import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.enums.EUserStatus;
import com.david.springsecrest.models.Restaurant;
import com.david.springsecrest.models.User;
import com.david.springsecrest.payload.request.UpdateUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;


public interface IRestaurantService {

    Page<Restaurant> getAll(Pageable pageable);

    Restaurant getById(UUID id);

    Restaurant create(Restaurant restaurant);

    Restaurant save(Restaurant restaurant);

//    Restaurant update(UUID id, UpdateUserDTO dto);

//    Page<Restaurant> getAllByType(Pageable pageable, ERestaurantType type);

    Page<Restaurant> searchRestaurant(Pageable pageable, String searchKey);
}
