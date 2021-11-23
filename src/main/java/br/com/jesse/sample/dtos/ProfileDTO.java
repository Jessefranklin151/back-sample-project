package br.com.jesse.sample.dtos;

import br.com.jesse.sample.models.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDTO {

    private Long id;

    private String name;

    private List<PermissionDTO> permissions = new ArrayList<>();

}
