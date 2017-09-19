package br.com.etqpadrao.etqpadrao.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

public class Filtros {
    private String lote;
    private Produto produto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dt_inicial;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dt_final;
    private int cx_inicial;
    private int cx_final;

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Calendar getDt_inicial() {
        return dt_inicial;
    }

    public void setDt_inicial(Calendar dt_inicial) {
        this.dt_inicial = dt_inicial;
    }

    public Calendar getDt_final() {
        return dt_final;
    }

    public void setDt_final(Calendar dt_final) {
        this.dt_final = dt_final;
    }

    public int getCx_inicial() {
        return cx_inicial;
    }

    public void setCx_inicial(int cx_inicial) {
        this.cx_inicial = cx_inicial;
    }

    public int getCx_final() {
        return cx_final;
    }

    public void setCx_final(int cx_final) {
        this.cx_final = cx_final;
    }
}
