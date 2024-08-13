package com.springproject.moa.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springproject.moa.board.entity.Boards;

public interface BoardRepository extends JpaRepository<Boards, Long> {

}
