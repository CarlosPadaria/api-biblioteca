package com.example.demo.services;

import com.example.demo.dto.AdministradorDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.Administrador;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.AdministradorRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public AdministradorDTO save(UsuarioDTO data){
        String encodedPassword = bCryptPasswordEncoder.encode(data.senha());
        Administrador register = new Administrador(data);
        register.setSenha(encodedPassword);
        Administrador pessoa = repository.save(register);
        return new AdministradorDTO( pessoa);
    }
}
