package com.example.demo.controllers;


import com.example.demo.dto.AdministradorDTO;
import com.example.demo.dto.UsuarioDTO;
import com.example.demo.models.Administrador;
import com.example.demo.models.Endereco;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.AdministradorRepository;
import com.example.demo.repositories.EnderecoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<?> getUsuarios(){
       var lista = usuarioRepository.findAll().stream().map(UsuarioDTO::new).collect(Collectors.toList());
         return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        else{
            return ResponseEntity.ok(usuario);
        }
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> postUsuarios(@Valid @RequestBody UsuarioDTO usuarioDTO){
    //    Usuario usuario = new Usuario(usuarioDTO);

        try {
            UsuarioDTO usuarioCreated = usuarioService.save(usuarioDTO);
            return ResponseEntity.status(201).body(usuarioCreated);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    // fazer o post de administrador com bcrypt
    @PostMapping("/admin")
    @Transactional
    public  ResponseEntity<?> postAdmins(@Valid @RequestBody UsuarioDTO usuarioDTO){

        try {
            AdministradorDTO adminCreated = adminService.save(usuarioDTO);
            return ResponseEntity.status(201).body(adminCreated);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUsuarios(@PathVariable("id") Long id,@Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        try {
            usuario.setNome(usuarioDTO.nome());
            usuario.setNumeroTelefone(usuarioDTO.numeroTelefone());
            usuario.setEmail(usuarioDTO.email());
            usuario.setSenha(usuarioDTO.senha());
            if(usuario.getEndereco() != null){
                Endereco endereco = new Endereco(usuarioDTO.endereco());
                endereco.setId_endereco(usuario.getEndereco().getId_endereco());
                usuario.setEndereco(endereco);
            }

            UsuarioDTO usuarioCreated = usuarioService.save(new UsuarioDTO(usuario));

            return ResponseEntity.ok(usuarioCreated);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuarios(@PathVariable("id") Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        try {
            usuarioRepository.delete(usuario);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }



}
