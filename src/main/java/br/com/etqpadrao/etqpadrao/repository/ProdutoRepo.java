package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepo extends CrudRepository <Produto, Long> {
}
