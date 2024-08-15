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
	
	@Override
    public void insertBoard(BoardDTO boardDTO) {
        Boards board = BoardMapper.toEntity(boardDTO);
        if (board == null) {
            System.out.println("BoardMapper.toEntity returned null");
            return;
        }
        try {
            boardRepository.save(board);
            System.out.println("Board saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving board: " + e.getMessage());
        }
    }
	
}
