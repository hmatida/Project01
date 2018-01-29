package br.com.etqpadrao.etqpadrao;

import br.com.etqpadrao.etqpadrao.storage.StorageService;
import br.com.etqpadrao.etqpadrao.utilidades.Inicia;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EtqpadraoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtqpadraoApplication.class, args);
	}
}
