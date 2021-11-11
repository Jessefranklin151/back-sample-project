package br.com.jesse.sample;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.enumerations.UserStatus;
import br.com.jesse.sample.models.User;
import br.com.jesse.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserService userService) {
        return (args) -> {
            String password = "123456";
            UserCreationDTO admin = new UserCreationDTO("Jessé", "jesseoito@gmail.com", password);
            userService.createUser(admin);
        };
    }

}
