package br.com.etqpadrao.etqpadrao.utilidades;

public class DigitoVerificadorGs1 {
    private Integer divisor;
    private Integer multiplier;
    private Integer three = 3;
    private Integer one = 1;
    private Integer digito;
    private Integer variable;
    private int flag;

    public String getDigito(String code){
        this.multiplier=0;
        flag=0;
        for(int i = 0; i<code.length(); i++){
            if(flag == 0) {
                this.variable = Character.getNumericValue(code.charAt(i));
                multiplier = multiplier + variable * three;
                flag = 1;
            }else {
                this.variable = Character.getNumericValue(code.charAt(i));
                multiplier = multiplier + variable * one;
                flag =0;
            }
        }

        return calculaDezena(multiplier).toString();
    }

    private Integer calculaDezena(Integer sum){
        divisor = sum/10;
        if((divisor*10)%sum == 0){
            digito=sum-divisor*10;
        }else {
            divisor=(divisor*10)+10;
            digito=divisor-sum;
        }
        return this.digito;
    }
}
