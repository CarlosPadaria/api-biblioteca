package com.example.demo.dto;

import com.example.demo.models.Administrador;
import com.example.demo.models.Endereco;
import jakarta.validation.constraints.NotEmpty;

public record AdministradorDTO (
        Boolean isAdmin,
        String nome,
        String numeroTelefone,
        String email,
        String senha,
        Endereco endereco
){
        public AdministradorDTO(Administrador pessoa) {
                this(pessoa.getIsAdmin(), pessoa.getNome(), pessoa.getNumeroTelefone(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getEndereco());
        }
}
