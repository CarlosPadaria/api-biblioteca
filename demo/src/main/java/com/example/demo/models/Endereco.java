package com.example.demo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_endereco;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String rua;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String numero;
    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String bairro;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String cidade;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String estado;

    public Endereco(Endereco endereco) {
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }
}
