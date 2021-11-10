package br.com.jesse.sample.services;

import java.util.List;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.dtos.UserUpdateDTO;

public interface UserService {

	UserDTO getUserById(Long id);

	List<UserDTO> getUsers();

	UserDTO createUser(UserCreationDTO dto);

	UserDTO updateUser(Long id, UserUpdateDTO dto);

	void disableUser(Long id);

}
