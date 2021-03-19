package com.pos.backend.GlobalResponseHandler;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pos.backend.utils.IPOSConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class POSResponse {
	public POSResponse() {
	}

	public POSResponse(Object resp) {
		this.resp = resp;
	}

	public POSResponse(String message, Object resp) {
		this.message = message;
		this.resp = resp;
	}

	public POSResponse(int status, String message, Object resp) {
		this.status = status;
		this.message = message;
		this.resp = resp;
	}

	int status = HttpStatus.OK.value();
	String message = IPOSConstants.MSG_SUCCESS;
	Object resp;

}
