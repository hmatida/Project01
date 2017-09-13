package br.com.etqpadrao.etqpadrao.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "etiqueta_produto")
public class EtiquetaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long num_caixa;
    private String lote;
    private String cod1;
    private String cod2;
    private String cod3;
    private String fab_string;
    private String val_string;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar fabricacao;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Calendar validade;

    @ManyToOne
    @JoinColumn(name="id_produto", referencedColumnName = "id_produto")
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getNum_caixa() {
        return num_caixa;
    }

    public void setNum_caixa(long num_caixa) {
        this.num_caixa = num_caixa;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getCod1() {
        return cod1;
    }

    public void setCod1(String cod1) {
        this.cod1 = cod1;
    }

    public String getCod2() {
        return cod2;
    }

    public void setCod2(String cod2) {
        this.cod2 = cod2;
    }

    public String getCod3() {
        return cod3;
    }

    public void setCod3(String cod3) {
        this.cod3 = cod3;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Calendar getFabricacao() {
        return fabricacao;
    }

    public String getFab_string() {
        return fab_string;
    }

    public void setFab_string(Calendar fab_string) {

        this.fab_string = calendarInString(this.fabricacao);
    }

    public String getVal_string() {
        return val_string;
    }

    public void setVal_string(Calendar val_string) {
        this.val_string = calendarInString(this.validade);
    }

    private String calendarInString(Calendar date){
        String day=Integer.toString(date.get(Calendar.DATE));
        String month = Integer.toString(date.get(Calendar.MONTH)+1);
        String year = Integer.toString(date.get(Calendar.YEAR));
        if (month.length()!= 2){
            month="0"+month;
        }
        if (day.length()!=2){
            day="0"+day;
        }

        return day+"/"+month+"/"+year;
    }

    public void setFabricacao(Calendar fabricacao) {
        this.fabricacao = fabricacao;
    }

    public Calendar getValidade() {
        return validade;
    }

    public void setValidade(Calendar validade) {
        this.validade = validade;
    }
}
