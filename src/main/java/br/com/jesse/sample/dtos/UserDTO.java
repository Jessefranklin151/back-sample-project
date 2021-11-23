package br.com.jesse.sample.dtos;

import br.com.jesse.sample.models.Profile;
import br.com.jesse.sample.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

	private Long id;

	private String name;

	private String email;

	private List<ProfileDTO> profile = new ArrayList<>();

}
