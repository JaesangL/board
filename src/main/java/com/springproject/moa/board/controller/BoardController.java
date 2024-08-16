package com.springproject.moa.board.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/board/insert")
	@ResponseBody
	public BoardDTO insertBoard(@RequestBody BoardDTO boardDTO) {
		boardDTO.setDate(LocalDate.now()); // date 필드를 현재 날짜로 설정
        boardService.insertBoard(boardDTO);
        return boardDTO;
    }

}
