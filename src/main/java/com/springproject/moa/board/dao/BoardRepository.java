package com.springproject.moa.board.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.moa.board.entity.Boards;

public interface BoardRepository extends JpaRepository<Boards, Long> {

	Optional<Boards> findById(Long id);
    Boards save(Boards board);
	
}
