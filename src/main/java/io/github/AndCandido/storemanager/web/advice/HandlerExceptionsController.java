package io.github.AndCandido.storemanager.web.advice;

import io.github.AndCandido.storemanager.api.exceptions.AuthenticationClientException;
import io.github.AndCandido.storemanager.api.exceptions.IllegalClientActionException;
import io.github.AndCandido.storemanager.api.exceptions.InsufficientStockException;
import io.github.AndCandido.storemanager.api.exceptions.ResourceNotFoundException;
import io.github.AndCandido.storemanager.api.response.ResponseError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlerExceptionsController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        var result = e.getBindingResult();

        List<String> errors = e.getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseError(errors)
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handlerProductNotFoundException(
            ResourceNotFoundException e
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseError(e.getMessage())
        );
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ResponseError> handlerInsufficientStockException(InsufficientStockException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseError(e.getMessage())
        );
    }

    @ExceptionHandler(IllegalClientActionException.class)
    public ResponseEntity<ResponseError> handlerIllegalSaleException(IllegalClientActionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseError(e.getMessage())
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseError> handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseError(e.getMessage())
        );
    }

    @ExceptionHandler(AuthenticationClientException.class)
    public ResponseEntity<ResponseError> handlerAuthenticationClientException(AuthenticationClientException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ResponseError(e.getMessage())
        );
    }

}