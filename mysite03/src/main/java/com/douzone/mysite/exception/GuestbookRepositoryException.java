package com.douzone.mysite.exception;

public class GuestbookRepositoryException extends RuntimeException {
	public GuestbookRepositoryException(String message) {
		super(message);
	}
	public GuestbookRepositoryException() {
		super("GuestbookRepositoryException");
	}
}
