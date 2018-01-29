package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.PassaDados;
import br.com.etqpadrao.etqpadrao.models.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;

public interface PassaDadosRepo extends CrudRepository<PassaDados, Long> {

    @Query("select p from PassaDados p WHERE p.dt_fabricacao=:dtFabricacao and p.produto=:produto")
    public PassaDados verificaSeGerouEtiqueta(@Param("dtFabricacao") Calendar dtFabricacao, @Param("produto")Produto produto);

    @Query("SELECT MAX(p.dt_fabricacao) FROM PassaDados p WHERE p.produto=:produto GROUP BY p.produto")
    public Calendar retornaDtFabricacaoMaisRecente(@Param("produto")Produto produto);
}
