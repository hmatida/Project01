package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaRepo extends CrudRepository <Empresa, Long> {
}
