package br.com.jesse.sample.services.impl;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.dtos.UserUpdateDTO;
import br.com.jesse.sample.enumerations.ErrorMessages;
import br.com.jesse.sample.enumerations.UserStatus;
import br.com.jesse.sample.models.User;
import br.com.jesse.sample.repositories.UserRepository;
import br.com.jesse.sample.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(user -> new UserDTO(user)).orElseThrow();
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO createUser(UserCreationDTO dto) {
        User user = userRepository.save(dto.toUser());
        return new UserDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id).orElseThrow();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return new UserDTO(userRepository.save(user));
    }

    @Override
    public void disableUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.DISABLED);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.EM0002.getMessage()));
    }
}
