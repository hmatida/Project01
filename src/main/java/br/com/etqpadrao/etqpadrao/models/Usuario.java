package br.com.etqpadrao.etqpadrao.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank (message = "É obrigatório cadastrar um nome")
    private String nome;

    @NotBlank (message = "É obrigatório o cadastro de um login.")
    private String login;

    @NotBlank(message = "É obrigatório o cadastro de uma senha")
    private String password;

    @Email(message = "É obrigatório o cadastro de um e-mail válido.")
    private String email;

    private String aprovado;

    private boolean ativo = false;

    @ManyToOne
    @JoinColumn(name="id_catusuario", referencedColumnName = "id_catusuario")
    private CategoriaUsuario cat_usuario;

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
        this.nome = nome.toUpperCase();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
        ativaUser();
    }

    public CategoriaUsuario getCat_usuario() {
        return cat_usuario;
    }

    public void setCat_usuario(CategoriaUsuario cat_usuario) {
        this.cat_usuario = cat_usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private void ativaUser(){
        if(this.aprovado.equals("Não"))
        this.ativo=false;
        else this.ativo=true;
    }
}
