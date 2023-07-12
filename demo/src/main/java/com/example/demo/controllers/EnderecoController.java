package com.example.demo.controllers;

import com.example.demo.models.Endereco;
import com.example.demo.repositories.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoRepository enderecoRepository;
    
    @GetMapping
    public ResponseEntity<?> getEnderecos() {
        return ResponseEntity.ok(enderecoRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEndereco(@PathVariable("id") Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElse(null);
        if(endereco == null){
            throw new EntityNotFoundException("Endereco não encontrado");
        }
        return ResponseEntity.ok(enderecoRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco enderecoUpdated = enderecoRepository.findById(id).orElse(null);
        if(enderecoUpdated == null){
            throw new EntityNotFoundException("Endereco não encontrado");
        }
        enderecoUpdated.setCidade(endereco.getCidade());
        enderecoUpdated.setEstado(endereco.getEstado());
        enderecoUpdated.setNumero(endereco.getNumero());
        enderecoUpdated.setRua(endereco.getRua());
        enderecoUpdated.setBairro(endereco.getBairro());
        enderecoRepository.save(enderecoUpdated);
        return ResponseEntity.ok(enderecoUpdated);
    }
}
