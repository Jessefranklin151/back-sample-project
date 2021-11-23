package br.com.jesse.sample.controllers;

import br.com.jesse.sample.dtos.PermissionDTO;
import br.com.jesse.sample.dtos.ProfileDTO;
import br.com.jesse.sample.services.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class SecurityController {

    private final SecurityService securityService;

    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("profiles")
    private ResponseEntity<List<ProfileDTO>> getProfiles() {
        return new ResponseEntity<>(securityService.getProfiles(), HttpStatus.OK);
    }

    @GetMapping("permission")
    private ResponseEntity<List<PermissionDTO>> getPermissions() {
        return new ResponseEntity<>(securityService.getPermissions(), HttpStatus.OK);
    }

}
