package com.springproject.moa.board.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED) //외부 접근 제한. entity로 객체(new) 생성 불가, 영속성을 위해 조작 제한
@Getter
public class Boards {
	
	@Id
	@Column(name = "id", length = 10, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가 설정
	private Long id;
	
	@Column(name = "title", length = 20, nullable = false)
	private String title;
	
	@Column(name = "author", length = 20, nullable = false)
	private String author;
	
	@Column(name = "content", length = 200, nullable = false)
	private String content;
	
	@Column(name = "date", length = 20, nullable = false)
	private LocalDate date;
	
	@Column(name = "views", length = 10, nullable = false)
	private int views;

	@Builder(builderMethodName = "boardBuilder")
	private Boards(long id, String title, String author, String content ,LocalDate date, int views) {

		this.id = id;
		this.title = title;
		this.author = author;
		this.content = content;
		this.date = date;
		this.views = views;
	}
	
	// 조회수 증가 메소드
	public void increaseViews() {
        this.views += 1;
    }
	

}
