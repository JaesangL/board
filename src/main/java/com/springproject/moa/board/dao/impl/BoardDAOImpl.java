package com.springproject.moa.board.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dao.BoardRepository;
import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;
import com.springproject.moa.board.entity.mapper.BoardMapper;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private BoardRepository boardRepository;
	
	//게시판 목록 조회
	@Override
	public List<BoardDTO> selectAll(){
        List<Boards> boards = boardRepository.findAll();
        List<BoardDTO> dto = new ArrayList<BoardDTO>();
        for (Boards board : boards) {
			dto.add(new BoardDTO(
					board.getId(),
					board.getTitle(),
					board.getAuthor(),
					board.getDate(),
					board.getViews()
					));
		}
        return dto;
	}
	
	//게시판 글쓰기
	@Override
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
	
}
