package br.com.jesse.sample.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.dtos.UserUpdateDTO;
import br.com.jesse.sample.enumerations.UserStatus;
import br.com.jesse.sample.models.User;
import br.com.jesse.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(user -> new UserDTO(user)).orElseThrow();
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public UserDTO createUser(UserCreationDTO dto) {
        String password = pe.encode(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), UserStatus.ACTIVE, password);
        return new UserDTO(userRepository.save(user));
    }

    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return new UserDTO(userRepository.save(user));
    }

    public void disableUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.DISABLED);
    }

}
