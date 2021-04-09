package com.searchproductos.searchproductos.controllers;

import com.searchproductos.searchproductos.dtos.ErrorDto;
import com.searchproductos.searchproductos.exceptionsHandlers.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//controller que responde con las excepciones
@RestController
public class BaseController
{
    @ExceptionHandler(BadParameterException.class)
    public ResponseEntity<?> handleException(BadParameterException e)
    {
        ErrorDto errorDto = new ErrorDto("Error en los parametros enviados", e.getMessage());

        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(ArticleNotFoundException e)
    {
        ErrorDto errorDto = new ErrorDto("Error de articulo", e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArticuloSinStockException.class)
    public ResponseEntity<ErrorDto> handleException(ArticuloSinStockException e)
    {
        ErrorDto errorDto = new ErrorDto("Error en la generacion del ticket", e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.OK);
    }

    @ExceptionHandler(CarritoNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(CarritoNotFoundException e)
    {
        ErrorDto errorDto = new ErrorDto("Error de carrito", e.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
