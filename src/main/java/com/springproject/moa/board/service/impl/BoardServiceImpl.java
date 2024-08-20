package com.springproject.moa.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.service.BoardService;

import jakarta.transaction.Transactional;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
    private BoardDAO boardDAO;

	// 게시판 목록 조회
    @Override
    public List<BoardDTO> getAllBoards() {
        return boardDAO.selectAll();
    }
    
    // 게시글 작성
    @Override
    @Transactional
    public void insertBoard(BoardDTO boardDTO) {
        boardDAO.insertBoard(boardDTO);
    }
    
    // 게시글 상세 페이지
    @Override
    public BoardDTO getBoardById(Long id) {
        return boardDAO.getBoardById(id);
    }
    
    // 조회수 증가
    @Override
    @Transactional
    public void increaseViews(Long id) {
    	boardDAO.increaseViews(id);
    }
    
    // 게시글 수정
    @Override
    @Transactional
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
	}
    
    // 게시글 삭제
    @Override
    @Transactional
    public void deleteBoard(Long id) {
        boardDAO.deleteBoard(id);
    }
}
