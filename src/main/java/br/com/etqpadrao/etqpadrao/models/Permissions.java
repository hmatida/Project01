package br.com.etqpadrao.etqpadrao.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String permission;

    @ManyToMany
    @JoinTable(name="permissions_categoria", joinColumns = @JoinColumn(name="id_permissions"),
            inverseJoinColumns=@JoinColumn(name="id_cat_usuario"))
    private List<CategoriaUsuario> categoriaUsuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
