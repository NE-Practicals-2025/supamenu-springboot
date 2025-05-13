package com.david.springsecrest.services.serviceImpls;


import com.david.springsecrest.enums.ERestaurantType;
import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.enums.EUserStatus;
import com.david.springsecrest.exceptions.BadRequestException;
import com.david.springsecrest.exceptions.ResourceNotFoundException;
import com.david.springsecrest.helpers.Utility;
import com.david.springsecrest.models.Restaurant;
import com.david.springsecrest.models.User;
import com.david.springsecrest.payload.request.UpdateUserDTO;
import com.david.springsecrest.repositories.IRestaurantRepository;
import com.david.springsecrest.repositories.IUserRepository;
import com.david.springsecrest.services.IRestaurantService;
import com.david.springsecrest.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements IRestaurantService {

    private final IUserRepository userRepository;
    private final IRestaurantRepository restaurantRepository;
//    private final IFileService fileService;
//    private final FileStorageService fileStorageService;

    @Override
    public Page<Restaurant> getAll(Pageable pageable) {
        return this.restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant getById(UUID id) {
        return this.restaurantRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Restaurant", "id", id.toString()));
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        try {
            Optional<Restaurant> restaurantOptional = this.restaurantRepository.findByRestaurantName(restaurant.getRestaurantName());
            if (restaurantOptional.isPresent())
                throw new BadRequestException(String.format("Restaurant with name '%s' already exists", restaurant.getRestaurantName()));
            return this.restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = Utility.getConstraintViolationMessage(ex, restaurant);
            throw new BadRequestException(errorMessage, ex);
        }
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        try {
            return this.restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException ex) {
            String errorMessage = Utility.getConstraintViolationMessage(ex, restaurant);
            throw new BadRequestException(errorMessage, ex);
        }
    }


//    @Override
//    public User update(UUID id, UpdateUserDTO dto) {
//        User entity = this.userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "id", id.toString()));
//
//        Optional<User> userOptional = this.userRepository.findByEmail(dto.getEmail());
//        if (userOptional.isPresent() && (userOptional.get().getId() != entity.getId()))
//            throw new BadRequestException(String.format("User with email '%s' already exists", entity.getEmail()));
//
//        entity.setEmail(dto.getEmail());
//        entity.setFirstName(dto.getFirstName());
//        entity.setLastName(dto.getLastName());
//        entity.setTelephone(dto.getTelephone());
//        entity.setGender(dto.getGender());
//
//        return this.userRepository.save(entity);
//    }

//    @Override
//    public Restaurant update(UUID id, UpdateUserDTO dto) {
//        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
//        user.setEmail(dto.getEmail());
//        user.setFirstName(dto.getFirstName());
//        user.setLastName(dto.getLastName());
//        user.setTelephone(dto.getTelephone());
//    }


    @Override
    public Page<Restaurant> searchRestaurant(Pageable pageable, String searchKey) {
        return this.restaurantRepository.searchRestaurant(pageable, searchKey);
    }


//    @Override
//    public User changeProfileImage(UUID id, File file) {
//        User entity = this.userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("Document", "id", id.toString()));
//        File existingFile = entity.getProfileImage();
//        if (existingFile != null) {
//            this.fileStorageService.removeFileOnDisk(existingFile.getPath());
//        }
//        entity.setProfileImage(file);
//        return this.userRepository.save(entity);
//
//    }

//    @Override
//    public User removeProfileImage(UUID id) {
//        User user = this.userRepository.findById(id).orElseThrow(
//                () -> new ResourceNotFoundException("User", "id", id.toString()));
//        File file = user.getProfileImage();
//        if (file != null) {
//            this.fileService.delete(file.getId());
//        }
//        user.setProfileImage(null);
//        return this.userRepository.save(user);
//    }
}
