package br.com.jesse.sample.models;

import br.com.jesse.sample.enumerations.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
	private static final long serialVersionUID = -8186669329140906037L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	private UserStatus status;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Profile> profiles = new HashSet<>();

	public User(String name, String email, UserStatus status) {
		this.name = name;
		this.email = email;
		this.status = status;
	}

	public User(String name, String email, UserStatus status, String password) {
		this.name = name;
		this.email = email;
		this.status = status;
		this.password = password;
	}

	public User(String name, String email, UserStatus status, String password, HashSet<Profile> profiles) {
		this.name = name;
		this.email = email;
		this.status = status;
		this.password = password;
		this.profiles = profiles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (Profile profile: profiles) {
			authorities.add(new SimpleGrantedAuthority(profile.getName().toUpperCase(Locale.ROOT)));
			for (Permission permission: profile.getPermissions()) {
				authorities.add(new SimpleGrantedAuthority(permission.getAuthority().toUpperCase(Locale.ROOT)));
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return status.equals(UserStatus.ACTIVE);
	}

}
