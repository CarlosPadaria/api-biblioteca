package com.example.demo.models;

import com.example.demo.dto.EmprestimoPostDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_emprestimo;

    @Column(unique = false, nullable = false, updatable = true)
    private LocalDate data_emprestimo;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Usuario usuario;

    public Emprestimo(EmprestimoPostDTO dto) {
        this.data_emprestimo = dto.data().toLocalDate();

    }
}
