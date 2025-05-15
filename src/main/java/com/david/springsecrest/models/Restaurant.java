package com.david.springsecrest.models;

import com.david.springsecrest.enums.ERestaurantType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.UUID;

@Entity()
@Table(name = "restaurant_profile")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID userId;
    private String name;
    private String completeName;
    private String contactNumber;
    private String ownerPhone;
    private String ownerEmail;
    private String ownerName;
    private ERestaurantType type;
    private File profileImage;

    public Restaurant(String restaurantName, ERestaurantType restaurantType, String restaurantCompleteName, String contactNumber, String ownerPhone, String ownerEmail, String ownerName) {
        this.name = restaurantName;
        this.type = restaurantType;
        this.completeName = restaurantCompleteName;
        this.contactNumber = contactNumber;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
    }

    public Restaurant(UUID userId, String restaurantName, ERestaurantType restaurantType, String restaurantCompleteName, String contactNumber, String ownerPhone, String ownerEmail, String ownerName) {
        this.name = restaurantName;
        this.type = restaurantType;
        this.completeName = restaurantCompleteName;
        this.contactNumber = contactNumber;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.userId = userId;
    }
}
