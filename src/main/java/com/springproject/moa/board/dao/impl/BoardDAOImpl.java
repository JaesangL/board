package com.springproject.moa.board.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dao.BoardRepository;
import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;
import com.springproject.moa.board.entity.mapper.BoardMapper;
import com.springproject.moa.board.excetpion.BoardNotFoundException;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private BoardRepository boardRepository;
	
	//게시판 목록 조회
	@Override
	public List<BoardDTO> selectAll(){
        List<Boards> boards = boardRepository.findAllByOrderByIdAsc();
        List<BoardDTO> dto = new ArrayList<BoardDTO>();
        for (Boards board : boards) {
			dto.add(new BoardDTO(
					board.getId(),
					board.getTitle(),
					board.getAuthor(),
					board.getContent(),
					board.getDate(),
					board.getViews()
					));
		}
        return dto;
	}
	
	//게시판 글쓰기
	@Override
	@Transactional
    public void insertBoard(BoardDTO boardDTO) {
        Boards board = BoardMapper.toEntity(boardDTO);
        if (board == null) {
            System.out.println("BoardMapper.toEntity returned null");
            return;
        }
        try {
            boardRepository.save(board);
            System.out.println("글쓰기 성공!");
        } catch (Exception e) {
            System.out.println("글쓰기 실패: " + e.getMessage());
        }
    }
	
	 //게시판 상세보기
	@Override
    public BoardDTO getBoardById(Long id) {
		Boards board = boardRepository.findById(id).orElseThrow(() -> 
        	new BoardNotFoundException("게시글이 존재하지 않습니다. ID: " + id));
        return new BoardDTO(
            board.getId(),
            board.getTitle(),
            board.getAuthor(),
            board.getContent(),
            board.getDate(),
            board.getViews()
        );
    }
	
	//조회수 증가
	@Override
	@Transactional
	public void increaseViews(Long id) {
		Boards board = boardRepository.findById(id)
		        .orElseThrow(() -> new BoardNotFoundException("게시글이 존재하지 않습니다. ID: " + id));
		board.increaseViews();
		boardRepository.save(board);
		System.out.println("조회수 증가 성공");
	}
	
	//게시판 수정
	@Override
	@Transactional
	public void updateBoard(BoardDTO boardDTO) {
		Boards board = boardRepository.findById(boardDTO.getId())
		        .orElseThrow(() -> new BoardNotFoundException("게시글이 존재하지 않습니다. ID: " + boardDTO.getId()));

	    // 존재하면 업데이트 수행
	    board = BoardMapper.toEntity(boardDTO);
	    if (board == null) {
	        System.out.println("BoardMapper.toEntity returned null");
	        return;
	    }
	    try {
	        boardRepository.save(board);
	        System.out.println("업데이트 성공!");
	    } catch (Exception e) {
	        System.out.println("업데이트 실패: " + e.getMessage());
	    }
	}
	
	//게시판 삭제
	@Override
    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
