package br.felipehenriques.archiveBoss.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	private long id;
	@NotBlank
	@Column(unique = true)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String name;
	@NotBlank
	@Column(unique = true)
	@Email
	private String email;

	public User() {}

	public User(long id, @NotBlank String username, @NotBlank String password, @NotBlank String name,
			@NotBlank @Email String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
