package com.pos.backend.GlobalResponseHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pos.backend.utils.IPOSConstants;

@ControllerAdvice
public class ExceptionService {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionService.class);

	@ExceptionHandler
	ResponseEntity<POSResponse> handleException(CustomException err) {
//		logger.error(err.getMessage());
		POSResponse CER = new POSResponse();
		CER.setStatus(err.getHttpStatus().value());
		CER.setMessage(err.getMessage());
		return new ResponseEntity<>(CER, err.getHttpStatus());
	}

	@ExceptionHandler
	ResponseEntity<POSResponse> handleException(Exception exe) {
		logger.error("System Error: " + exe.getStackTrace());

		POSResponse CER = new POSResponse();
		CER.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		CER.setMessage(IPOSConstants.MSG_SYSTEM_ERROR);
		return new ResponseEntity<>(CER, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
