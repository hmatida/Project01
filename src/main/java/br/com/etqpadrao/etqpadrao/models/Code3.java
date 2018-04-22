package br.com.etqpadrao.etqpadrao.models;

import br.com.etqpadrao.etqpadrao.controllers.AddZeros;
import br.com.etqpadrao.etqpadrao.utilidades.DigitoVerificadorGs1;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Calendar;

public class Code3 {
    private String fabricacao;
    private String validade;
    private String peso_liquido;
    private String lote;
    private String cx_seq;
    private String qtd_emb_caixa;
    private String sscc;

    public String geraCodeBP(PassaDados passaDados){
        fabricacao= transf_for_string(passaDados.getDt_fabricacao());
        validade=transf_for_string(gera_validade(passaDados));
        AddZeros addZeros = new AddZeros();
        String taste=transfPeso1(passaDados.getProduto().getPeso_liquido());
        peso_liquido=addZeros.adiciona(taste, 6);
        cx_seq=addZeros.adiciona(Long.toString(passaDados.getProduto().getNumero_caixa()), 8);
        DataLote dataLote = new DataLote();
        lote=addZeros.adiciona(dataLote.geraLote(passaDados.getDt_fabricacao()),8);
        return fabricacao+validade+peso_liquido+lote+cx_seq;
    }

    /*
    * Gera CODE3 para produto Bom Peixe 250g/ PAC131
    * */
    public String geraCodeBPPAC131(PassaDados passaDados){
        fabricacao= transf_for_string(passaDados.getDt_fabricacao());
        validade=transf_for_string(gera_validade(passaDados));
        AddZeros addZeros = new AddZeros();
        String taste=transfPeso1(passaDados.getProduto().getQtd_prod_na_caixa());
        qtd_emb_caixa=addZeros.adiciona(taste, 6);
        cx_seq=addZeros.adiciona(Long.toString(passaDados.getProduto().getNumero_caixa()), 8);
        DataLote dataLote = new DataLote();
        lote=addZeros.adiciona(dataLote.geraLote(passaDados.getDt_fabricacao()),8);
        return fabricacao+validade+qtd_emb_caixa+lote+cx_seq;
    }

    /*
     * Gera a validade de um produto
     */
    private Calendar gera_validade(PassaDados passaDados){
        Calendar venc = Calendar.getInstance();
        venc.set(passaDados.getDt_fabricacao().get(Calendar.YEAR),
                passaDados.getDt_fabricacao().get(Calendar.MONTH),
                passaDados.getDt_fabricacao().get(Calendar.DAY_OF_MONTH));
        venc.add(Calendar.MONTH, passaDados.getProduto().getValidade());
        return venc;
    }
    /*
     * Retira os "/" de um data e retorna em uma String
     */
    private String transf_for_string (Calendar calendar){
        String data = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        if (data.length()<2){
            data="0"+data;
        }
        String variable=Integer.toString(calendar.get(Calendar.MONTH)+1);
        if (variable.length()<2){
            variable="0"+variable;
        }
        data=data+variable;
        variable=Integer.toString((calendar.get(Calendar.YEAR)));
        data=data+variable;
        return data;
    }
    /*
     * Transforma variável peso líquido Double em uma String e a retorna
     * padrão Bom Peixe
     */
    private String transfPeso1(double valor){
        String variable = "";
        double valor2=valor;
            valor2 = valor2 * Math.pow(10, 2);
            int temp = (int) valor2;
            variable= Integer.toString(temp);
            return variable;
    }

    public String geraCod3Qualita(PassaDados passaDados){
        this.sscc=passaDados.getProduto().getCliente().getEmpresa().getSscc();
        AddZeros addZeros = new AddZeros();
        String qtdCaixas = addZeros.adiciona(Long.toString(passaDados.getProduto().getNumero_caixa()), 12);
        String code = this.sscc+qtdCaixas;
        DigitoVerificadorGs1 digitoGs1 = new DigitoVerificadorGs1();
        String digito = digitoGs1.getDigito(code);
        return code+digito;
    }
}
