package com.springproject.moa.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dto.BoardDTO;
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
    	boardDTO.setId(null);// id 필드를 null로 설정하여 insert시 자동으로 id가 생성되도록 함
        boardDAO.insertBoard(boardDTO);
    }
}
