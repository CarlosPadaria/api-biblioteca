package com.example.demo.controllers;

import com.example.demo.dto.LivroPostDTO;
import com.example.demo.models.Livro;
import com.example.demo.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<?> getLivros() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getLivro(@PathVariable("id") Long id){
        Livro livro = livroRepository.findById(id).orElse(null);
        if(livro == null){
            throw new EntityNotFoundException("Livro não encontrado");
        }
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("pesquisa") String pesquisa, @RequestParam("valor") String valor){
        if(valor == null || valor.isBlank() || valor.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos");
        }
        if(pesquisa == null || pesquisa.isBlank() || pesquisa.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos");
        }
        if(pesquisa.equals("palavraChave"))
        {
            return ResponseEntity.ok(livroRepository.findAllByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(valor, valor));
        }
        else if(pesquisa.equals("autor"))
        {
            return ResponseEntity.ok(livroRepository.findAllByAutorContainingIgnoreCase(valor));
        }
        else if(pesquisa.equals("editora"))
        {
            return ResponseEntity.ok(livroRepository.findAllByEditoraContainingIgnoreCase(valor));
        }
        else if(pesquisa.equals("titulo"))
        {
            return ResponseEntity.ok(livroRepository.findAllByTituloContainingIgnoreCase(valor));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos");

    }

    @PostMapping()
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody LivroPostDTO DTO) {
        Livro livro = new Livro(DTO);
        try {
            Livro livroCreated = livroRepository.save(livro);
            return ResponseEntity.status(201).body(livroCreated);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
           // return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> update(@RequestBody LivroPostDTO DTO, @PathVariable("id") Long id) {

        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
           throw new EntityNotFoundException("Livro não encontrado");
        }
        if(DTO.titulo() != null &&  (!DTO.titulo().isBlank() || !DTO.titulo().isEmpty())){
            livro.setTitulo(DTO.titulo());
        }
        if(DTO.editora() != null && (!DTO.editora().isBlank() || !DTO.editora().isEmpty())){
            livro.setEditora(DTO.editora());
        }
        if(DTO.autor() != null && (!DTO.autor().isBlank() || !DTO.autor().isEmpty())){
            livro.setAutor(DTO.autor());
        }
        if(DTO.dataPublicacao() != null){
            livro.setDataPublicacao(DTO.dataPublicacao());
        }

        try {
            Livro livroUpdated = livroRepository.save(livro);
            return ResponseEntity.status(200).body(livroUpdated);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Livro livro = livroRepository.findById(id).orElse(null);
        if (livro == null) {
            throw new EntityNotFoundException("Livro não encontrado");
        }
        try {
            livroRepository.delete(livro);
            return ResponseEntity.status(200).body("Livro deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
