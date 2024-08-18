package com.springproject.moa.board.excetpion;

public class BoardNotFoundException extends RuntimeException {
	
	public BoardNotFoundException(String message) {
        super(message);
    }

}
