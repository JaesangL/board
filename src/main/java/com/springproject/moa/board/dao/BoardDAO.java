package com.springproject.moa.board.dao;

import java.util.List;

import com.springproject.moa.board.dto.BoardDTO;

public interface BoardDAO {

	public List<BoardDTO> selectAll();
	
	public void insertBoard(BoardDTO boardDTO);
	
//	public boolean updateBoard();
//	
//	public boolean deleteBoard();
	
}
