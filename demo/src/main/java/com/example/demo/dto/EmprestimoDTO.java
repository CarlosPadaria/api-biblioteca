package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmprestimoDTO (

        Long id_emprestimo,
        @NotNull
        LocalDateTime data


){
}
