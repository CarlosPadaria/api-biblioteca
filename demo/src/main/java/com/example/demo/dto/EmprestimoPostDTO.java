package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EmprestimoPostDTO (

        @NotNull
        LocalDateTime data,

        @NotNull
        Long id_livro,

        @NotNull
        Long id_usuario
) {

}
