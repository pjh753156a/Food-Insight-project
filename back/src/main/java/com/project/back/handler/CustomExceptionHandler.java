package com.project.back.handler;

import com.project.back.dto.response.ResponseDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;


@RestControllerAdvice
public class CustomExceptionHandler 
{
  @ExceptionHandler({
    MethodArgumentNotValidException.class,
    HttpMessageNotReadableException.class
  })
  public ResponseEntity<ResponseDto> validationExceptionHandler (Exception exception) 
  {
    exception.printStackTrace();
    return ResponseDto.validationFailed();
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ResponseDto> noHandlerFoundExceptionHandler (Exception exception) 
  {
    exception.printStackTrace();
    return ResponseDto.noExistUser();
  }
}
/*분석완료*/