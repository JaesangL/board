package com.springproject.moa.board.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springproject.moa.board.dao.BoardDAO;
import com.springproject.moa.board.dao.BoardRepository;
import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private BoardRepository boardRepository;
	
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
        System.out.println(dto);
        System.out.println(dto.getClass().getName());
        return dto;
	}
	
}
