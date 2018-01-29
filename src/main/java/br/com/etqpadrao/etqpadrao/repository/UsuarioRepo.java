package br.com.etqpadrao.etqpadrao.repository;

import br.com.etqpadrao.etqpadrao.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepo extends CrudRepository<Usuario, Long> {

    @Query("select us FROM Usuario us WHERE us.login LIKE :login")
    public Usuario veriricaLogin(@Param("login")String login);

}
