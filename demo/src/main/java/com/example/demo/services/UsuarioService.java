package com.example.demo.services;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    public UsuarioDTO save(UsuarioDTO data){
        String encodedPassword = bCryptPasswordEncoder.encode(data.senha());
        Usuario register = new Usuario(data);
        register.setSenha(encodedPassword);
        Usuario pessoa = this.repository.save(register);
        return new UsuarioDTO(  pessoa);
    }
}
