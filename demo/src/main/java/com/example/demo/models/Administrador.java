package com.example.demo.models;


import com.example.demo.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador extends Usuario{

    @Column(unique = false, updatable = true)
    private Boolean isAdmin = true;
    public Administrador(UsuarioDTO usuarioDTO) {
        setNome(usuarioDTO.nome());
        setEndereco(usuarioDTO.endereco());
        setEmail(usuarioDTO.email());
        setNumeroTelefone(usuarioDTO.numeroTelefone());
        setSenha(usuarioDTO.senha());
        setPapel(Papel.ROLE_ADMIN);
    }
}
