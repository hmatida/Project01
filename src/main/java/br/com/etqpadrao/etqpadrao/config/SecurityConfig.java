package br.com.etqpadrao.etqpadrao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args){
        System.out.println(new BCryptPasswordEncoder().encode("Hcm1605"));
    }
}
