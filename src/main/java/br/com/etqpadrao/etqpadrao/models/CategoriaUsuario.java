package br.com.etqpadrao.etqpadrao.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cat_usuario")
public class CategoriaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_catusuario")
    private Long id;

    @NotBlank (message = "Obrigatório o nome da categoria de usuário.")
    private String nome;

    @OneToMany(mappedBy = "cat_usuario")
    private List<Usuario> usuarios;

    @ManyToMany
    private Set<Permissions> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
