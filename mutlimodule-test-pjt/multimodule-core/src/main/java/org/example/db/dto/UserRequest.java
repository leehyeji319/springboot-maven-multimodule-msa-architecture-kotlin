package org.example.db.dto;

import org.example.db.entity.User;

import lombok.Data;

@Data
public class UserRequest {

	String name;
	String id;
	String password;

	public User toEntity() {
		return User.builder()
			.name(this.name)
			.id(this.id)
			.password(this.password)
			.build();
	}
}
