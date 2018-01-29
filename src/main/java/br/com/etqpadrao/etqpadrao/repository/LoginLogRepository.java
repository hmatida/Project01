package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.LoginLog;
import org.springframework.data.repository.CrudRepository;

public interface LoginLogRepository extends CrudRepository<LoginLog, Long> {
}
