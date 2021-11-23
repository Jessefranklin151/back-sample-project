package br.com.jesse.sample.dtos;

import br.com.jesse.sample.enumerations.UserStatus;
import br.com.jesse.sample.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDTO {

	private String name;

	private String email;

	private String password;

}
