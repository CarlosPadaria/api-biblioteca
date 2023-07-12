package com.example.demo.dto;

import com.example.demo.models.Endereco;
import com.example.demo.models.Usuario;
import jakarta.validation.constraints.NotEmpty;



public record UsuarioDTO(
        @NotEmpty
        String nome,
        @NotEmpty
        String numeroTelefone,
        @NotEmpty
        String email,
        @NotEmpty
        String senha,

        Endereco endereco

) {

        public UsuarioDTO(Usuario pessoa) {
                this(pessoa.getNome(), pessoa.getNumeroTelefone(), pessoa.getEmail(), pessoa.getSenha(), pessoa.getEndereco());
        }
}
