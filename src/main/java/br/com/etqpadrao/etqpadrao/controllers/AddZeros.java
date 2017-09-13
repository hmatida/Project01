package br.com.etqpadrao.etqpadrao.controllers;

public class AddZeros {

    public String adiciona (String dados, int qtd_zeros){
        if (dados.length()<qtd_zeros){
            char [] charArray = dados.toCharArray();
            char [] charReturn = new char[qtd_zeros];
            int i=0;
            while (i<qtd_zeros){
                charReturn[i]='0';
                i++;
            }
            i--;
            for (int j=charArray.length-1; j>=0; j--){
                charReturn[i]=charArray[j];
                i--;
            }
            dados="";
            i=0;
            while (i<charReturn.length){
                dados=dados+charReturn[i];
                i++;
            }
        }
        return dados;
    }
}
