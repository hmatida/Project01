package br.com.etqpadrao.etqpadrao.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cliente")
    private Long id;

    @NotBlank(message = "Razão social não pode ficar em branco." )
    private String razao_social;

    @CNPJ(message = "CNPJ é obrigatório.")
    @NotNull(message = "CNPJ é obrigatório.")
    private String cnpj;

    @NotNull(message = "Nome fantasia é obrigatório.")
    private String nome_fantasia;

    @NotNull(message = "Obrigatório a seleção de uma empresa.")
    @ManyToOne
    @JoinColumn(name="id_empresa", referencedColumnName = "id_empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "cliente")
    private List<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social.toUpperCase();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia.toUpperCase();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
