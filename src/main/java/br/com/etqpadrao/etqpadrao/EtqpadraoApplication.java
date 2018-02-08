package br.com.etqpadrao.etqpadrao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class EtqpadraoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EtqpadraoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EtqpadraoApplication.class);
	}
}
