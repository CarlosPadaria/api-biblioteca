package com.example.demo.models;


import com.example.demo.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @Enumerated(EnumType.STRING)
    private Papel papel = Papel.ROLE_USER;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String nome;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String numeroTelefone;

    @Column(length = 255, unique = true , nullable = false, updatable = true)
    private String email;

    @Column(length = 255, unique = false, nullable = false, updatable = true)
    private String senha;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Endereco endereco;

    public Usuario(UsuarioDTO usuarioDTO) {
        this.nome = usuarioDTO.nome();
        this.numeroTelefone = usuarioDTO.numeroTelefone();
        this.email = usuarioDTO.email();
        this.senha = usuarioDTO.senha();
        this.endereco = usuarioDTO.endereco();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(papel.toString()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
