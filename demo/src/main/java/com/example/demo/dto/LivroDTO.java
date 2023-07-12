package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record LivroDTO (
        Long id_livro,
        @NotEmpty
        String titulo,
        @NotEmpty
        String autor,
        @NotEmpty
        String editora,
        @NotEmpty
        LocalDate dataPublicacao
){
}
