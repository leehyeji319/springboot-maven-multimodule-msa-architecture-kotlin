package org.example;

import org.example.db.dto.UserRequest;
import org.example.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void addUser(UserRequest request) {
		userRepository.save(request.toEntity());
	}
}
