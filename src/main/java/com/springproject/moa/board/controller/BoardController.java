package com.springproject.moa.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board/main") //localhost:8080/board/main
	public String boardList(Model model) {
		List<BoardDTO> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "boardMain";
//        return boardService.getAllBoards();
	}

	@GetMapping("/board/write") //localhost:8080/board/write
	public String boardWriteForm() {
		return "boardWrite";
	}

}
