package com.springproject.moa.board.dao;

import java.util.List;

import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;

public interface BoardDAO {

	public List<BoardDTO> selectAll();
	
	public void insertBoard(BoardDTO boardDTO);

	public BoardDTO getBoardById(Long id);
	
	public void increaseViews(Long id);

//	public boolean updateBoard();
//	
//	public boolean deleteBoard();
	
}
