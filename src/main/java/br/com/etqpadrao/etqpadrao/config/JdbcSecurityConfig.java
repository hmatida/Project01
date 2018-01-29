package br.com.etqpadrao.etqpadrao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class JdbcSecurityConfig {

    public static final String USUARIO_POR_LOGIN = "SELECT login, password, ativo, nome FROM usuario WHERE login = ?";

    public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.permission as nome_permissao FROM usuario AS u JOIN cat_usuario AS f ON u.id_catusuario=f.id_catusuario"
            + " JOIN permissions_categoria AS pf ON f.id_catusuario=pf.id_cat_usuario"
            + " JOIN permissions AS p ON pf.id_permissions=p.id WHERE login = ?";

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder,
                                DataSource dataSource, PasswordEncoder passwordEncoder
                                ) throws Exception {
        builder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(USUARIO_POR_LOGIN)
                .authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO);
    }
}
