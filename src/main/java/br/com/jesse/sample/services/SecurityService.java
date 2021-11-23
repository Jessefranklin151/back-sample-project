package br.com.jesse.sample.services;

import br.com.jesse.sample.dtos.PermissionDTO;
import br.com.jesse.sample.dtos.ProfileDTO;
import br.com.jesse.sample.repositories.PermissionRepository;
import br.com.jesse.sample.repositories.ProfileRepository;
import br.com.jesse.sample.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityService {

    private final ProfileRepository profileRepository;
    private final PermissionRepository permissionRepository;

    public SecurityService(ProfileRepository profileRepository, PermissionRepository permissionRepository) {
        this.profileRepository = profileRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionDTO> getPermissions() {
        return permissionRepository.findAll().stream().map(Mapper::toPermissionDTO).collect(Collectors.toList());
    }

    public List<ProfileDTO> getProfiles() {
        return profileRepository.findAll().stream().map(Mapper::toProfileDTO).collect(Collectors.toList());
    }

}
