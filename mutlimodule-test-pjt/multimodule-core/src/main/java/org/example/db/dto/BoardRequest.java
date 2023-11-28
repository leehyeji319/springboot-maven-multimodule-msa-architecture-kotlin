package org.example.db.dto;

import org.example.db.entity.Board;
import org.example.db.entity.User;

import lombok.Data;

@Data
public class BoardRequest {

	String title;
	String content;
	Long userId;

	public Board toEntity(User user) {
		return Board.builder()
			.title(this.title)
			.content(this.content)
			.user(user)
			.build();
	}
}
