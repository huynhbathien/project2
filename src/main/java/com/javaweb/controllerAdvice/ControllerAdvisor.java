package com.javaweb.controllerAdvice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaweb.model.ErrorResponseDTO;

import customexception.FieldRequiredException;

@ControllerAdvice
class ControllerAdvisor extends ResponseEntityExceptionHandler{
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(ArithmeticException ex,  WebRequest request) {
			ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO();
			errorResponseDTO.setError(ex.getMessage());
			List<String> listdetail=new ArrayList<>();
			listdetail.add("lỗi toán học");
			errorResponseDTO.setDetail(listdetail);
	        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	@ExceptionHandler(FieldRequiredException.class)
	public ResponseEntity<Object> handFielRespone(FieldRequiredException ex,WebRequest request)
	{
		ErrorResponseDTO errorResponseDT=new ErrorResponseDTO();
		errorResponseDT.setError(ex.getMessage());
		List<String> listError=new ArrayList<String>();
		listError.add("check Data in Field");
		errorResponseDT.setDetail(listError);
		return new ResponseEntity<>(errorResponseDT,HttpStatus.BAD_GATEWAY);
	}
}
