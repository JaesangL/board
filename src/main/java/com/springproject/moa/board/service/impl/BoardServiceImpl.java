package com.springproject.moa.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;
import com.springproject.moa.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
    private BoardDAO boardDAO;

    @Override
    public List<BoardDTO> getAllBoards() {
        return boardDAO.selectAll();
    }
    
    @Override
    public void insertBoard(BoardDTO boardDTO) {
        boardDAO.insertBoard(boardDTO);
    }
    
    @Override
    public BoardDTO getBoardById(Long id) {
        return boardDAO.getBoardById(id);
    }
    
}
