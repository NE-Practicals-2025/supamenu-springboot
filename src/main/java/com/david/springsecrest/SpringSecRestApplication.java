package com.david.springsecrest;

import com.david.springsecrest.enums.ERole;
import com.david.springsecrest.models.Role;
import com.david.springsecrest.repositories.IRoleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringSecRestApplication {
    private final IRoleRepository roleRepository;
    public SpringSecRestApplication(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecRestApplication.class, args);
    }

    @Bean
    public boolean registerRoles() {
        Set<ERole> roles = new HashSet<>();
        roles.add(ERole.ADMIN);
        roles.add(ERole.OWNER);
        roles.add(ERole.CLIENT);

        for (ERole role : roles) {
            Optional<Role> roleByName = roleRepository.findByName(role);
            if (roleByName.isEmpty()) {
                Role newRole = new Role(role, role.toString());
                System.out.println(newRole);
                roleRepository.save(newRole);
                System.out.println("Created: " + role);
            }
        }
        return true;
    }
}
