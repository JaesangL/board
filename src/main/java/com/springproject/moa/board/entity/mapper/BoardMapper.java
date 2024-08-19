package com.springproject.moa.board.entity.mapper;

import com.springproject.moa.board.dto.BoardDTO;
import com.springproject.moa.board.entity.Boards;

public class BoardMapper {
	
	public static Boards toEntity(BoardDTO dto) {
		
		if(dto == null) {
			System.out.println("no data in DTO");
			return null;
		}
		
//		return Boards.boardBuilder()
////				.id(dto.getId()) id 자동증가로 인해 주석처리
//				.title(dto.getTitle())
//				.author(dto.getAuthor())
//				.content(dto.getContent())
//				.date(dto.getDate())
//				.views(dto.getViews())
//				.build();
		
		Boards.BoardsBuilder builder = Boards.boardBuilder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .content(dto.getContent())
                .date(dto.getDate())
                .views(dto.getViews());
        
        // Update or Delete 동작에 필요한 id 값이 취득
        if (dto.getId() != null) {
            builder.id(dto.getId());
        }

        return builder.build();
	}
	
	public static BoardDTO toDTO(Boards board) {
        if (board == null) {
            System.out.println("no data in entity");
            return null;
        }
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setAuthor(board.getAuthor());
        dto.setContent(board.getContent());
        dto.setDate(board.getDate());
        dto.setViews(board.getViews());
        return dto;
    }

}
