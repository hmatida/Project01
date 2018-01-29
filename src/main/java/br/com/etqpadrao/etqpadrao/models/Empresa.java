package br.com.etqpadrao.etqpadrao.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_empresa")
    private Long id;

    @NotNull (message = "CNPJ não pode ficar em branco.")
    @CNPJ
    private String cnpj;

    @NotBlank (message = "Razão social é obrigatório.")
    private String razao_social;

    @NotBlank (message = "Nome fantasia é obrigatório.")
    private String nome_fantasia;
    @NotBlank(message = "Endereço é obrigatório.")
    private String endereco;

    @NotBlank(message = "Bairro é obrigatório.")
    private String bairro;

    @NotNull (message = "Número é obrigatório.")
    private int numero;
    private String complemento;
    private int reg_processador;

    @OneToMany(mappedBy = "empresa")
    private List <Cliente> clientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social.toUpperCase();
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia.toUpperCase();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.toUpperCase();
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.toUpperCase();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento.toUpperCase();
    }

    public int getReg_processador() {
        return reg_processador;
    }

    public void setReg_processador(int reg_processador) {
        this.reg_processador = reg_processador;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
