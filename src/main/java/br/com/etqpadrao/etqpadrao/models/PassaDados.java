package br.com.etqpadrao.etqpadrao.models;

import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "passa_dados")
public class PassaDados {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_passa_dados")
    private Long id;

    @NotNull(message = "Data de fabricação é obrigatório.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dt_fabricacao;

    @NotNull(message = "Obrigatório colocar quantidade de caixas.")
    @Min(value = 1, message = "A quantidade de caixas deve ser maior que 0.")
    private int num_caixas;

    @ManyToOne
    @JoinColumn(name="id_produto", referencedColumnName = "id_produto")
    @NotNull(message = "Obigtório a seleção de um produto.")
    private Produto produto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dtGeracao;

    private String logUserGeado;

    public Calendar getDt_fabricacao() {
        return dt_fabricacao;
    }

    public void setDt_fabricacao(Calendar dt_fabricacao) {

        this.dt_fabricacao = dt_fabricacao;
        setaDtGeracao();
    }

    public int getNum_caixas() {
        return num_caixas;
    }

    public void setNum_caixas(int num_caixas) {
        this.num_caixas = num_caixas;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogUserGeado() {
        return logUserGeado;
    }

    public void setLogUserGeado(String logUserGeado) {
        this.logUserGeado = logUserGeado;
    }

    public Calendar getDtGeracao() {
        return dtGeracao;
    }

    private void setaDtGeracao(){
        Calendar now = Calendar.getInstance();
        this.dtGeracao=now;
    }
}
