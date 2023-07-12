package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public record LivroPostDTO(
        @NotEmpty
        String titulo,
        @NotEmpty
        String autor,
        @NotEmpty
        String editora,
        @NotNull
        LocalDate dataPublicacao
) {
}
