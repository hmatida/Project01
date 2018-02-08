package br.com.etqpadrao.etqpadrao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "br.com.etqpadrao.etqpadrao")
public class AppWebConfiguration {
    @Bean
    public FormattingConversionService mvcConversionService(){
        DefaultFormattingConversionService conversionService =
                new DefaultFormattingConversionService(true);
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        registrar.registerFormatters(conversionService);
        return conversionService;
    }
}
