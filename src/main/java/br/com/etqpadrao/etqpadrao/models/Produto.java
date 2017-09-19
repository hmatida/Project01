package br.com.etqpadrao.etqpadrao.models;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Long id;

    private String nome_produto;
    private double peso_liquido;
    private double peso_bruto;
    private double tara_embalagem;
    private double tara_caixa;
    private double tara_total;
    private long numero_caixa;
    private int validade;
    private String etq_layout;

    @ManyToOne
    @JoinColumn(name="id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "produto")
    private List <EtiquetaProduto> etiquetaProdutos;


    @OneToMany(mappedBy = "produto")
    private List<PassaDados> passaDados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public double getPeso_liquido() {
        return peso_liquido;
    }

    public void setPeso_liquido(double peso_liquido) {
        this.peso_liquido = peso_liquido;
    }

    public double getPeso_bruto() {
        return peso_bruto;
    }

    public void setPeso_bruto(double peso_bruto) {
        this.peso_bruto = peso_bruto;
    }

    public double getTara_embalagem() {
        return tara_embalagem;
    }

    public void setTara_embalagem(double tara_embalagem) {
        this.tara_embalagem = tara_embalagem;
    }

    public double getTara_caixa() {
        return tara_caixa;
    }

    public void setTara_caixa(double tara_caixa) {
        this.tara_caixa = tara_caixa;
    }

    public double getTara_total() {
        return tara_total;
    }

    public void setTara_total(double tara_total) {
        this.tara_total = tara_total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<EtiquetaProduto> getEtiquetaProdutos() {
        return etiquetaProdutos;
    }

    public void setEtiquetaProdutos(List<EtiquetaProduto> etiquetaProdutos) {
        this.etiquetaProdutos = etiquetaProdutos;
    }

    public int getValidade() {
        return validade;
    }

    public void setValidade(int validade) {
        this.validade = validade;
    }

    public long getNumero_caixa() {
        return numero_caixa;
    }

    public void setNumero_caixa(long numero_caixa) {
        this.numero_caixa = numero_caixa;
    }

    public List<PassaDados> getPassaDados() {
        return passaDados;
    }

    public void setPassaDados(List<PassaDados> passaDados) {
        this.passaDados = passaDados;
    }

    public String getEtq_layout() {
        return etq_layout;
    }

    public void setEtq_layout(String etq_layout) {
        this.etq_layout = etq_layout;
    }
}
