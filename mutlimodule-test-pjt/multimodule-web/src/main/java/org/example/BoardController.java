package org.example;

import org.example.db.dto.BoardRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ResponseEntity<?> addBoard(@RequestBody BoardRequest request) {
		boardService.addBoard(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
