package com.example.demo.controllers;

import com.example.demo.dto.EmprestimoPostDTO;
import com.example.demo.models.Emprestimo;
import com.example.demo.models.Livro;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.EmprestimoRepository;
import com.example.demo.repositories.LivroRepository;
import com.example.demo.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> getEmprestimos() {
        return ResponseEntity.ok(emprestimoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmprestimo(@PathVariable("id") Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if (emprestimo == null) {
           throw new EntityNotFoundException("Emprestimo não encontrado");
        } else {
            return ResponseEntity.ok(emprestimo);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody EmprestimoPostDTO DTO) {
        Emprestimo emprestimo = new Emprestimo(DTO);

        Usuario usuario = usuarioRepository.findById(DTO.id_usuario()).get();
        Livro livro = livroRepository.findById(DTO.id_livro()).get();

        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        if(livro == null){
            throw new EntityNotFoundException("Livro não encontrado");
        }

        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        try {
            Emprestimo emprestimoCreated = emprestimoRepository.save(emprestimo);
            return ResponseEntity.ok(emprestimoCreated);
        }catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody EmprestimoPostDTO DTO){
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if(emprestimo == null){
            throw new EntityNotFoundException("Emprestimo não encontrado");
        }
        Usuario usuario = usuarioRepository.findById(DTO.id_usuario()).get();
        Livro livro = livroRepository.findById(DTO.id_livro()).get();

        if(usuario == null){
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        if(livro == null){
            throw new EntityNotFoundException("Livro não encontrado");
        }
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        try {
            Emprestimo emprestimoUpdated = emprestimoRepository.save(emprestimo);
            return ResponseEntity.ok(emprestimoUpdated);
        }
        catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElse(null);
        if(emprestimo == null){
            String message = String.format("Emprestimo com id %s não encontrado", id);
           return ResponseEntity.status(404).body(message);
        }
        try {
            emprestimoRepository.deleteById(id);
            return ResponseEntity.status(200).body("Deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
