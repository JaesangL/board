package com.springproject.moa.board.service;

import java.util.List;

import com.springproject.moa.board.dto.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> getAllBoards();
	public void insertBoard(BoardDTO boardDTO);
	public BoardDTO getBoardById(Long id);
	public void increaseViews(Long id);
	public void updateBoard(BoardDTO boardDTO);
	public void deleteBoard(Long id);

}
