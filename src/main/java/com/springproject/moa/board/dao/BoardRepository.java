package com.springproject.moa.board.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.moa.board.entity.Boards;

public interface BoardRepository extends JpaRepository<Boards, Long> {

	List<Boards> findAllByOrderByIdAsc(); //게시판 목록 조회
	Optional<Boards> findById(Long id); //게시판 상세보기
    Boards save(Boards board); //게시판 글쓰기
	
	
}
