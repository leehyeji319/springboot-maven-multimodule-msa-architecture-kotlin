package org.example.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long userId;

	@Column
	private String name;

	@Column
	private String id;

	@Column
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Board> boards = new ArrayList<>();

	@Builder(toBuilder = true)
	public User(String name, String id, String password, List<Board> boards) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.boards = boards;
	}
}
