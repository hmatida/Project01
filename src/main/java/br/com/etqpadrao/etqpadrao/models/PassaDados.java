package br.com.etqpadrao.etqpadrao.models;

import org.springframework.format.annotation.DateTimeFormat;
import sun.rmi.runtime.Log;

import javax.persistence.*;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Calendar dt_fabricacao;
    private int num_caixas;

    @ManyToOne
    @JoinColumn(name="id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    public Calendar getDt_fabricacao() {
        return dt_fabricacao;
    }

    public void setDt_fabricacao(Calendar dt_fabricacao) {
        this.dt_fabricacao = dt_fabricacao;
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
}
