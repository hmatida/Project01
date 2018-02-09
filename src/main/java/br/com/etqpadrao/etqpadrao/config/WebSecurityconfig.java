package br.com.etqpadrao.etqpadrao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/webjars/**","/css/**", "/less/**","/img/**","/js/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/usuario/cad-cat-usuario").hasAnyRole("3")
                .antMatchers("/usuario/list-user").hasAnyRole("3")
                .antMatchers("/empresa").hasAnyRole("3")
                .antMatchers("/cliente").hasAnyRole("3")
                .antMatchers("/produto").hasAnyRole("2")
                .antMatchers("/usuario").hasAnyRole("1")
                .antMatchers("/usuario/form-input").permitAll()
                .antMatchers("/usuario/save").permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

    protected void configure(AuthenticationManagerBuilder builder
    ) throws Exception {
        builder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery(USUARIO_POR_LOGIN)
                .authoritiesByUsernameQuery(PERMISSOES_POR_USUARIO);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    public static final String USUARIO_POR_LOGIN = "SELECT login, password, ativo, nome FROM usuario WHERE login = ?";

    public static final String PERMISSOES_POR_USUARIO = "SELECT u.login, p.permission as nome_permissao FROM usuario AS u JOIN cat_usuario AS f ON u.id_catusuario=f.id_catusuario"
            + " JOIN permissions_categoria AS pf ON f.id_catusuario=pf.id_cat_usuario"
            + " JOIN permissions AS p ON pf.id_permissions=p.id WHERE login = ?";
}
