package com.example.demo.dto;

import org.springframework.validation.FieldError;

public record CampoErroDTO (String campo, String erro) {
    public CampoErroDTO (FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
