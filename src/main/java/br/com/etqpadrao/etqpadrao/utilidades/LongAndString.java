package br.com.etqpadrao.etqpadrao.utilidades;

public class LongAndString {

    private String number;

    private String description;

    public LongAndString() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long returnNumberInLong(){
        Long num = Long.parseLong(this.number);
        return num;
    }
}
