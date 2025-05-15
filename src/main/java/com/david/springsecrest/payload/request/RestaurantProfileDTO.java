package com.david.springsecrest.payload.request;


import com.david.springsecrest.enums.ERestaurantType;
import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.helpers.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
public class RestaurantProfileDTO {

    @Email
    private String email;

    @NotBlank
    private String restaurantName;

    @NotBlank
    private String restaurantCompleteName;

    @NotBlank
    private ERestaurantType restaurantType;

    @NotBlank
    private String ownerName;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,12}", message = "Your phone is not a valid tel we expect 2507***, or 07*** or 7***")
    private String contactNumber;

    @NotBlank
    @Pattern(regexp = "[0-9]{9,12}", message = "Your phone is not a valid tel we expect 2507***, or 07*** or 7***")
    private String ownerPhone;

    @NotBlank
    private UUID userId;

}