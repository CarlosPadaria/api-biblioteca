package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public record EnderecoDTO (
    Long id_endereco,
    @NotEmpty
    String rua,
    @NotEmpty
    String numero,
    @NotEmpty
    String bairro,
    @NotEmpty
    String cidade,
    @NotEmpty
    String estado
){
}
