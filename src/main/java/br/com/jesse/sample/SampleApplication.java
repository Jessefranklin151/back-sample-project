package br.com.jesse.sample;

import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.models.Permission;
import br.com.jesse.sample.models.Profile;
import br.com.jesse.sample.repositories.PermissionRepository;
import br.com.jesse.sample.repositories.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.services.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner runner(UserService userService, ProfileRepository profileRepository, PermissionRepository permissionRepository) {
        return (args) -> {

            Profile adminProfile = new Profile();
            adminProfile.setName("ROLE_ADMIN");
            Profile userProfile = new Profile();
            userProfile.setName("ROLE_USER");

            profileRepository.saveAll(Arrays.asList(adminProfile, userProfile));

            Permission updateUserPermission = permissionRepository.save(new Permission("UPDATE_USER"));
            Permission basicOperationsPermission = permissionRepository.save(new Permission("BASIC_OPERATIONS"));
            adminProfile.getPermissions().add(updateUserPermission);
            userProfile.getPermissions().add(basicOperationsPermission);
            profileRepository.saveAll(Arrays.asList(adminProfile, userProfile));

            String password = "123456";
            UserCreationDTO admin = new UserCreationDTO("Jessé", "jesseoito@gmail.com", password);
//
//            String password2 = "123456";
//            UserCreationDTO admin2 = new UserCreationDTO("Jessica", "jessica@gmail.com", password2);
//
//            String password3 = "123456";
//            UserCreationDTO commonUser = new UserCreationDTO("Heldao", "heldao@gmail.com", password3);
//
            UserDTO user = userService.createUser(admin);
//            UserDTO user1 = userService.createUser(commonUser);
//            UserDTO user2 = userService.createUser(admin2);
//
            userService.updateUserProfileByUserID(user.getId(), adminProfile);
//            userService.updateUserProfileByUserID(user1.getId(), userProfile);
//            userService.updateUserProfileByUserID(user2.getId(), adminProfile);
        };
    }

}
