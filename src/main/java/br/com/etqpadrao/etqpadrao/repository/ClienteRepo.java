package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepo extends CrudRepository <Cliente, Long > {
}
