package br.com.etqpadrao.etqpadrao.utilidades;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

public class ProdutoGerado {

    private String nomeProduto;

    private String codigo;

    private double peso_liquido;

    private double peso_bruto;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dtFabricacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dtLastEtiq;

    private String geradoPor;

    private int qtdCxGerada;

    public ProdutoGerado(String nomeProduto, String codigo, double peso_liquido, double peso_bruto, Calendar dtFabricacao, Calendar dtLastEtiq, String geradoPor, int qtdCxGerada) {
        this.nomeProduto = nomeProduto;
        this.codigo = codigo;
        this.peso_liquido = peso_liquido;
        this.peso_bruto = peso_bruto;
        this.dtFabricacao = dtFabricacao;
        this.dtLastEtiq = dtLastEtiq;
        this.geradoPor = geradoPor;
        this.qtdCxGerada = qtdCxGerada;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Calendar getDtLastEtiq() {
        return dtLastEtiq;
    }

    public void setDtLastEtiq(Calendar dtLastEtiq) {
        this.dtLastEtiq = dtLastEtiq;
    }

    public String getGeradoPor() {
        return geradoPor;
    }

    public void setGeradoPor(String geradoPor) {
        this.geradoPor = geradoPor;
    }

    public int getQtdCxGerada() {
        return qtdCxGerada;
    }

    public void setQtdCxGerada(int qtdCxGerada) {
        this.qtdCxGerada = qtdCxGerada;
    }


    public Calendar getDtFabricacao() {
        return dtFabricacao;
    }

    public void setDtFabricacao(Calendar dtFabricacao) {
        this.dtFabricacao = dtFabricacao;
    }
}
