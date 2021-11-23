package br.com.jesse.sample.utils;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jesse.sample.dtos.PermissionDTO;
import br.com.jesse.sample.dtos.ProfileDTO;
import br.com.jesse.sample.dtos.UserDTO;
import br.com.jesse.sample.models.Permission;
import br.com.jesse.sample.models.Profile;
import br.com.jesse.sample.models.User;

public class Mapper {

    public static UserDTO toUserDTO(User user) {
        List<ProfileDTO> profileDTOS = user.getProfiles().stream().map(Mapper::toProfileDTO).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), profileDTOS);
    }

    public static ProfileDTO toProfileDTO(Profile profile) {
        List<PermissionDTO> permissionDTOS = profile.getPermissions().stream().map(Mapper::toPermissionDTO).collect(Collectors.toList());
        return new ProfileDTO(profile.getId(), profile.getName(), permissionDTOS);
    }

    public static PermissionDTO toPermissionDTO(Permission permission) {
        return new PermissionDTO(permission.getId(), permission.getName());
    }


}
