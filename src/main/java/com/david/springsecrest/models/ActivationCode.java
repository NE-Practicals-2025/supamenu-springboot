package com.david.springsecrest.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String activationCode;

    @JsonIgnore
    private LocalDateTime activationCodeExpiresAt;

    public ActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public ActivationCode(String activationCode, LocalDateTime activationCodeExpiresAt) {
        this.activationCode = activationCode;
        this.activationCodeExpiresAt = activationCodeExpiresAt;
    }
}
