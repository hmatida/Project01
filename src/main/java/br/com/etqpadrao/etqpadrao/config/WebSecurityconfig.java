package br.com.etqpadrao.etqpadrao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityconfig extends WebSecurityConfigurerAdapter {

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
}
