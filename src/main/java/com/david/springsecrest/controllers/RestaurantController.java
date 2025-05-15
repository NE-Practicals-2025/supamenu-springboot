package com.david.springsecrest.controllers;

import com.david.springsecrest.enums.ERestaurantType;
import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.exceptions.BadRequestException;
import com.david.springsecrest.helpers.Constants;
import com.david.springsecrest.models.Restaurant;
import com.david.springsecrest.models.Role;
import com.david.springsecrest.models.User;
import com.david.springsecrest.payload.request.CreateUserDTO;
import com.david.springsecrest.payload.request.RestaurantProfileDTO;
import com.david.springsecrest.payload.request.UpdateUserDTO;
import com.david.springsecrest.payload.response.ApiResponse;
import com.david.springsecrest.repositories.IRoleRepository;
import com.david.springsecrest.repositories.IUserRepository;
import com.david.springsecrest.services.IRestaurantService;
import com.david.springsecrest.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    private final IRestaurantService restaurantService;
    private final IUserRepository userRepository;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public RestaurantController(IRestaurantService restaurantService, IUserRepository userRepository) {
        this.restaurantService = restaurantService;
        this.userRepository = userRepository;
    }

//    @PutMapping(path = "/update")
//    public ResponseEntity<ApiResponse> update(@RequestBody UpdateUserDTO dto) {
//        User updated = this.restaurantService.update(this.userService.getLoggedInUser().getId(), dto);
//        return ResponseEntity.ok(ApiResponse.success("User updated successfully", updated));
//    }

    @GetMapping(path = "/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> getAllRestaurants(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return ResponseEntity.ok(ApiResponse.success("Restaurants fetched successfully", restaurantService.getAll(pageable)));
    }

//    @GetMapping(path = "/all/{type}")
//    public ResponseEntity<ApiResponse> getAllUsersByRole(
//            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
//            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit,
//            @PathVariable(value = "type") ERestaurantType restaurantType
//    ) {
//        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
//        return ResponseEntity.ok(ApiResponse.success("Users fetched successfully", userService.getAllByRole(pageable, role)));
//    }

    @GetMapping(path = "/search")
    public Page<Restaurant> searchRestaurant(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit,
            @RequestParam(value = "searchKey") String searchKey
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return restaurantService.searchRestaurant(pageable, searchKey);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(ApiResponse.success("Restaurant fetched successfully", this.restaurantService.getById(id)));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RestaurantProfileDTO dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> new BadRequestException("User with " + dto.getUserId() + " not set"));
        Restaurant restaurant = new Restaurant(dto.getUserId(), dto.getRestaurantName(), dto.getRestaurantType(), dto.getRestaurantCompleteName(), dto.getContactNumber(), dto.getOwnerPhone(), dto.getEmail(), dto.getOwnerName());

        Restaurant entity = this.restaurantService.create(restaurant);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().toString());
        return ResponseEntity.created(uri).body(ApiResponse.success("Restaurant profile created successfully", entity));
    }
}
