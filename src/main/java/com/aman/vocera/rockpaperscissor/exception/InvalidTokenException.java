package com.aman.vocera.rockpaperscissor.exception;

public class InvalidTokenException extends RuntimeException{

	public InvalidTokenException() {
		super();
	}
	
	public InvalidTokenException(String message){
		super(message);
	}
}
