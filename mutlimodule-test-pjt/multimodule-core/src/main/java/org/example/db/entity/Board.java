package org.example.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private Long boardId;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Builder(toBuilder = true)
	public Board(String title, String content, User user) {
		this.title = title;
		this.content = content;
		this.user = user;
	}
}
