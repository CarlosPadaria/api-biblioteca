package com.example.demo.models;

import com.example.demo.dto.LivroPostDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_livro;

    @Column(length = 255, unique = false, nullable = true , updatable = true)
    private String titulo;

    @Column(length = 255, unique = false, nullable = true, updatable = true)
    private String autor;

    @Column(length = 255, unique = false, nullable = true, updatable = true)
    private String editora;

    @Column(unique = false, nullable = true, updatable = true)
    private LocalDate dataPublicacao;

    public Livro(LivroPostDTO dto) {
        setAutor(dto.autor());
        setEditora(dto.editora());
        setDataPublicacao(dto.dataPublicacao());
        setTitulo(dto.titulo());
    }
}
