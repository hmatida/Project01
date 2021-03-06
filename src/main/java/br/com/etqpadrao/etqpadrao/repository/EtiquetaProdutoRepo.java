package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.EtiquetaProduto;
import br.com.etqpadrao.etqpadrao.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface EtiquetaProdutoRepo extends JpaRepository<EtiquetaProduto, Long>{
    /*
     * Faz um SELECT com base em um intervalo de datas e produto.
     * Recebe como parâmetro um intervalo de datas em formato Calendar e objeto produto.
     */
    @Query("select etq from EtiquetaProduto etq where etq.fabricacao between :dtInicial and :dtFinal and etq.produto=:pro and etq.ativa like 'Ativa'")
    public List<EtiquetaProduto> findByDataInterval(@Param("dtInicial") Calendar Inicial, @Param("dtFinal")
            Calendar dtFinal, @Param("pro") Produto pro);

    /*
    * Faz um SELECT com base em um intervalo número de caixas geradas o número de etiquetas.
    * Recebe como parâmetro um intervalo de caixas em formato long e objeto produto.
    * Traz somente as etiquetas ativas
    */
    @Query("select etq from EtiquetaProduto etq where etq.num_caixa between :cxInicial and :cxFinal and etq.produto=:pro and etq.ativa like 'Ativa'")
    public List<EtiquetaProduto> findByBoxInterval(@Param("cxInicial") long cxInicial, @Param("cxFinal")
            long cxFinal, @Param("pro") Produto pro);

    /*
    * Faz um SELECT com base em um lote.
    * Recebe como parâmetro um lote de caixas em formato String e objeto produto.
    */
    @Query("select etq from EtiquetaProduto etq where etq.lote like :lote and etq.produto=:pro and etq.ativa like 'Ativa'")
    public List<EtiquetaProduto> findByLote(@Param("lote") String lote, @Param("pro") Produto pro);

    /*
     *Sobreescrita do médtodo all
      *  */
    @Query("select etq from EtiquetaProduto etq WHERE etq.ativa like 'Ativa'")
    public List<EtiquetaProduto> findAllActive();

    /*
     * Faz um SELECT com base em um intervalo de datas e produto.
     * Recebe como parâmetro um intervalo de datas em formato Calendar e objeto produto.
     */
    @Query("select etq from EtiquetaProduto etq where etq.fabricacao between :dtInicial and :dtFinal and etq.produto=:pro")
    public List<EtiquetaProduto> findByDataIntervalAll(@Param("dtInicial") Calendar Inicial, @Param("dtFinal")
            Calendar dtFinal, @Param("pro") Produto pro);

    /*
   * Faz um SELECT com base em um intervalo número de caixas geradas o número de etiquetas.
   * Recebe como parâmetro um intervalo de caixas em formato long e objeto produto.
   * Traz todas as etiquetas, canceladas ou não.
   */
    @Query("select etq from EtiquetaProduto etq where etq.num_caixa between :cxInicial and :cxFinal and etq.produto=:pro")
    public List<EtiquetaProduto> findByBoxIntervalAll(@Param("cxInicial") long cxInicial, @Param("cxFinal")
            long cxFinal, @Param("pro") Produto pro);

    /*
    * Faz um SELECT com base em um lote.
    * Recebe como parâmetro um lote de caixas em formato String e objeto produto.
    * Retorna uma lista com todas as etiquetas em qualquer estado.
    */
    @Query("select etq from EtiquetaProduto etq where etq.lote like :lote and etq.produto=:pro")
    public List<EtiquetaProduto> findByLoteAll(@Param("lote") String lote, @Param("pro") Produto pro);
}
