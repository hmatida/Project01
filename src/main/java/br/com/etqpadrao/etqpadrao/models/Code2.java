package br.com.etqpadrao.etqpadrao.models;

public class Code2 {

    private String lote;
    private Integer number;

    public String geraCod2Qualita(PassaDados passaDados){
        DataLote dtL = new DataLote();
        this.lote=dtL.geraLote(passaDados.getDt_fabricacao());
        number= passaDados.getProduto().getCliente().getEmpresa().getReg_processador();
        return "(7030)0"+number.toString()+
                    "(10)"+"00"+this.lote;
    }
}
