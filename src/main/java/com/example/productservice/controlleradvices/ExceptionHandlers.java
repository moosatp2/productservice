package com.example.productservice.controlleradvices;

import com.example.productservice.dtos.ExceptionDto;
import com.example.productservice.exceptions.ProductNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotExistException.class)
    public ResponseEntity<ExceptionDto> handleProductNotExistException(
            ProductNotExistException productNotExistException) {
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage(productNotExistException.getMessage());
        dto.setDetails("Details: Entered product ID is invalid in the URL check again");

        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> handleHttpNotReadableException(HttpMessageNotReadableException exception){
        ExceptionDto dto = new ExceptionDto();
        dto.setMessage("Invalid JSON Body");
        dto.setDetails("Details: invalid JSON body is not readable by HTTP engine ");

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
