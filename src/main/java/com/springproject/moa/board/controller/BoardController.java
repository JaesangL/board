package com.springproject.moa.board.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// 게시판 메인 페이지
	@GetMapping("/board/main") //localhost:8080/board/main
	public String boardList(Model model) {
		List<BoardDTO> boards = boardService.getAllBoards();
		model.addAttribute("boards", boards);
		return "boardMain";
//        return boardService.getAllBoards();
	}

	// 게시글 작성 페이지
	@GetMapping("/board/write") //localhost:8080/board/write
	public String boardWriteForm() {
		return "boardWrite";
	}
	
	// 게시글 작성
	@PostMapping("/board/insert")
	@ResponseBody
	public BoardDTO insertBoard(@RequestBody BoardDTO boardDTO) {
		boardDTO.setDate(LocalDate.now()); // date 필드를 현재 날짜로 설정
        boardService.insertBoard(boardDTO);
        return boardDTO;
    }
	
	/// 게시글 상세 페이지
	@GetMapping("/board/detail/{id}")
    public String getBoardDetail(@PathVariable("id") Long id, Model model) {
		boardService.increaseViews(id); // 조회수 증가
        BoardDTO boardDTO = boardService.getBoardById(id);
        model.addAttribute("board", boardDTO);
        return "boardDetail";
    }
	
	// 게시글 수정 페이지
	@PutMapping("/board/update")
    @ResponseBody
    public String updateBoard(@RequestBody BoardDTO boardDTO) {
        try {
        	boardDTO.setDate(LocalDate.now()); // date 필드를 현재 날짜로 설정
            boardService.updateBoard(boardDTO);
            return "게시글 수정 성공";
        } catch (Exception e) {
            return "게시글 수정 실패";
        }
    }
}
