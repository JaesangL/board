package com.springproject.moa.board.entity.mapper;

import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;

public class BoardMapper {
	
	public static Boards toEntity(BoardDTO dto) {
		
		if(dto == null) {
			System.out.println("no data in DTO");
			return null;
		}
		
		return Boards.boardBuilder()
				.id(dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.date(dto.getDate())
				.views(dto.getViews())
				.build();
	}

}
