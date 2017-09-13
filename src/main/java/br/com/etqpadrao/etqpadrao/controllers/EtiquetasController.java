package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.repository.EtiquetaProdutoRepo;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/etiquetas")
public class EtiquetasController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EtiquetaProdutoRepo etiquetaProdutoRepo;

    @GetMapping("/bompeixe_sato")
    public ModelAndView bomPeixeSato(){
        JasperReportsPdfView view = new JasperReportsPdfView();
        view.setUrl("classpath:report/etiqueta_bompeixe.jrxml");
        view.setApplicationContext(applicationContext);
        ModelAndView modelAndView = new ModelAndView(view);

//        Map<String, Object> params = new HashMap<>();
//        params.put("datasource", etiquetaProdutoRepo.findAll());
        modelAndView.addObject(etiquetaProdutoRepo.findAll());

        return modelAndView;
    }
}
