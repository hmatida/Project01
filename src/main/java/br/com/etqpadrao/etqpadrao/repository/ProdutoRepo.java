package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.PassaDados;
import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.utilidades.ProdutoGerado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepo extends CrudRepository <Produto, Long> {

    @Query(value = "SELECT new br.com.etqpadrao.etqpadrao.utilidades.ProdutoGerado(p.nome_produto, p.cod_produto,"
            +" p.peso_liquido, p.peso_bruto, pd.dt_fabricacao, pd.dtGeracao, pd.logUserGeado, pd.num_caixas) FROM"
            +" Produto p JOIN p.passaDados pd ORDER BY pd.dtGeracao DESC")
    public List<ProdutoGerado> listUltProdGerado();

}
