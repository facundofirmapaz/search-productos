package com.searchproductos.searchproductos.dtos.mappers;

import com.searchproductos.searchproductos.dtos.ResultDto;
import com.searchproductos.searchproductos.entities.Result;

public class ResultDtoMapper
{
    public static ResultDto mapToDto(Result r)
    {
        return new ResultDto(r.getCode(), r.getMessage());
    }
}
