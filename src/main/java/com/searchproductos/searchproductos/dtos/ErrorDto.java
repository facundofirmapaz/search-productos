package com.searchproductos.searchproductos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDto
{
    private String tipo;
    private String mensaje;
}
