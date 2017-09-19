package br.com.etqpadrao.etqpadrao.models;

import java.util.Calendar;
import java.util.Date;

public class DataLote {

    private String lote;
    private String letra_lote;
    private String dia_producao;
    private int mes;

    public String geraLote(Calendar data){
        mes=data.get(2);
//        System.out.println("Mes="+mes);
        if (mes==0){
            letra_lote="A";
        } else if (mes==1){
            letra_lote="B";
        } else if (mes==2){
            letra_lote="C";
        } else if (mes==3){
            letra_lote="D";
        } else if (mes==4){
            letra_lote="E";
        } else if (mes==5){
            letra_lote="F";
        } else if (mes==6) {
            letra_lote = "G";
        } else if (mes==7) {
            letra_lote = "H";
        } else if (mes==8) {
            letra_lote = "I";
        } else if (mes==9) {
            letra_lote = "J";
        } else if (mes==10) {
            letra_lote = "K";
        } else {
            letra_lote = "L";
        }
        dia_producao=Integer.toString(data.get(Calendar.DAY_OF_MONTH));
        if (dia_producao.length()!=2){
            dia_producao="0"+dia_producao;
        }
        String ano = Integer.toString(data.get(Calendar.YEAR));
        char [] charArray=ano.toCharArray();
        ano=""+charArray[charArray.length-1];
        lote= letra_lote+ano+dia_producao;
        return lote;
    }
}
