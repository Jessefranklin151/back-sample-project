package br.com.jesse.sample.controllers;

import br.com.jesse.sample.dtos.UserCreationDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.dtos.UserUpdateDTO;
import br.com.jesse.sample.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationDTO dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        return new ResponseEntity<>(userService.updateUser(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.disableUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
