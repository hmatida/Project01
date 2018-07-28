package br.com.etqpadrao.etqpadrao.models;

import java.util.Calendar;

public class Code1 {

    private String fabricacao;
    private String validade;

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

    public String geraCodQualita(PassaDados passaDados){
        this.validade=setaDateQualita(gera_validade(passaDados));
        this.fabricacao=setaDateQualita(passaDados.getDt_fabricacao());
        String gtin=passaDados.getProduto().getCliente().getEmpresa().getGtin();
        return "(01)"+gtin+"(15)"+this.validade+"(11)"+this.fabricacao;
    }

    private String setaDateQualita(Calendar date){
        Integer anoInt=date.get(Calendar.YEAR);
        anoInt=anoInt-2000;
        Integer monthInt=date.get(Calendar.MONTH)+1;
        Integer dayInt=date.get(Calendar.DAY_OF_MONTH);
        String month=monthInt.toString();
        String day=dayInt.toString();
        if(month.length()<2){
            month="0"+month;
        }
        if(day.length()<2){
            day="0"+day;
        }
        return day+month+anoInt.toString();
    }
}
