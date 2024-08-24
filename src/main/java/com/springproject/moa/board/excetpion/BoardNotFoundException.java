package com.springproject.moa.board.excetpion;

public class BoardNotFoundException extends RuntimeException {
	

	private static final long serialVersionUID = 5705107219217979551L;

	public BoardNotFoundException(String message) {
        super(message);
    }

}
