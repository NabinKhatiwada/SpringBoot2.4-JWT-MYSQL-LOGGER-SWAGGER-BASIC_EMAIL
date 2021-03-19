package com.pos.backend.GlobalResponseHandler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomException(HttpStatus httpStatus, String err) {
		super(err);
		this.httpStatus = httpStatus;
	}

	private HttpStatus httpStatus;
}
