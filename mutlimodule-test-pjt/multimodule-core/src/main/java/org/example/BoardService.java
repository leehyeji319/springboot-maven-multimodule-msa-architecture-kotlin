package org.example;

import org.example.db.dto.BoardRequest;
import org.example.db.entity.User;
import org.example.db.repository.BoardRepository;
import org.example.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	public void addBoard(BoardRequest request) {
		Long userId = request.getUserId();
		User findUser = userRepository.findById(userId).orElseThrow(
			() -> new IllegalArgumentException("user not found")
		);

		boardRepository.save(request.toEntity(findUser));
	}
}
