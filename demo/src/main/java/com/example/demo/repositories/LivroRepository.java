package com.example.demo.repositories;

import com.example.demo.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findAllByTitulo(String titulo);

    List<Livro> findAllByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor);
    List<Livro> findAllByAutorContainingIgnoreCase(String autor);
    List<Livro> findAllByTituloContainingIgnoreCase(String titulo);
    List<Livro> findAllByEditoraContainingIgnoreCase(String editora);

}
