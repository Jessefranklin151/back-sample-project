package br.com.jesse.sample.services;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.dtos.UserUpdateDTO;
import br.com.jesse.sample.enumerations.UserStatus;
import br.com.jesse.sample.models.Profile;
import br.com.jesse.sample.models.User;
import br.com.jesse.sample.repositories.UserRepository;
import br.com.jesse.sample.utils.Mapper;

@Service
public class UserService {

    private UserRepository userRepository;

    private final BCryptPasswordEncoder pe;

    private final ProfileService profileService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder pe, ProfileService profileService) {
        this.userRepository = userRepository;
        this.pe = pe;
        this.profileService = profileService;
    }

    private User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserDTO getUserById(Long id) {
       return Mapper.toUserDTO(getById(id));
    }

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(Mapper::toUserDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(UserCreationDTO dto) {
        String password = pe.encode(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), UserStatus.ACTIVE, password);
        user = userRepository.save(user);
        Profile p = new Profile();
        p.setName("USER");
        p = profileService.createProfileIfNotExists(p);
        HashSet profiles = new HashSet();
        profiles.add(p);
        userRepository.save(user);
        return Mapper.toUserDTO(user);
    }

    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = getById(id);
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return Mapper.toUserDTO(userRepository.save(user));
    }

    public void disableUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(UserStatus.DISABLED);
    }

    @Transactional
    public User updateUserProfileByUserID(Long userId, Profile profile) {
        User user = getById(userId);
        user.getProfiles().add(profile);
        return user;
    }

}
