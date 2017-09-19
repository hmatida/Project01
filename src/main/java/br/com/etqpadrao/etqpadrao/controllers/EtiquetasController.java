package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.funcionais.CalendarForString;
import br.com.etqpadrao.etqpadrao.models.EtiquetaProduto;
import br.com.etqpadrao.etqpadrao.models.Filtros;
import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.repository.EtiquetaProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.core.Application;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/etiquetas")
public class EtiquetasController {

    private final Path rootLocation;

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired

    private ProdutoRepo produtoRepo;

    @Autowired
    private EtiquetaProdutoRepo etiquetaProdutoRepo;

    public EtiquetasController() {
        rootLocation = null;
    }

    @GetMapping("/filtros")
    public ModelAndView pagina_filtros(){
        ModelAndView modelAndView = new ModelAndView("etiqueta/imprime_etiq_lote");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list_etiquetas(){
        ModelAndView modelAndView = new ModelAndView("etiqueta/list_etiquetas");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/impressao_avulsa/{id}")
    public ModelAndView impressao_avulsa(@PathVariable("id") Long id){
        EtiquetaProduto etiquetaProduto = etiquetaProdutoRepo.findOne(id);
        Produto produto=etiquetaProduto.getProduto();
        return printOne(etiquetaProduto, produto);
    }

    @PostMapping("/list_etq")
    public ModelAndView list_etiquetas_completo(@Valid Filtros filtros){
        ModelAndView modelAndView = new ModelAndView("etiqueta/list_etiquetas");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        modelAndView.addObject("etiquetas", etiquetaProdutoRepo.findByDataInterval(filtros.getDt_inicial(),
                filtros.getDt_final(), filtros.getProduto()));
        return modelAndView;
    }

    @PostMapping("/filtro_1")
    public ModelAndView filtro1_1(@Valid Filtros filtros){
        return bomPeixeSato(etiquetaProdutoRepo.findByDataInterval(filtros.getDt_inicial(), filtros.getDt_final(),
                filtros.getProduto()), filtros.getProduto());
    }

    @PostMapping("/filtro_2")
    public ModelAndView filtro2_1(@Valid Filtros filtros){
        return bomPeixeSato(etiquetaProdutoRepo.findByBoxInterval(filtros.getCx_inicial(), filtros.getCx_final(),
                filtros.getProduto()), filtros.getProduto());
    }

    @PostMapping("/filtro_3")
    public ModelAndView filtro3_1(@Valid Filtros filtros){
        return bomPeixeSato(etiquetaProdutoRepo.findByLote(filtros.getLote(), filtros.getProduto()),
                filtros.getProduto());
    }

    private ModelAndView bomPeixeSato(List<EtiquetaProduto> etiquetaProdutos, Produto produto){
        JasperReportsPdfView view = new JasperReportsPdfView();

        String url="classpath:report/"+produto.getEtq_layout();
        view.setUrl(url);
        view.setApplicationContext(applicationContext);
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(etiquetaProdutos);

        return modelAndView;
    }

    private ModelAndView printOne(EtiquetaProduto etiquetaProdutos, Produto produto){
        JasperReportsPdfView view = new JasperReportsPdfView();

        String url="classpath:report/"+produto.getEtq_layout();
        view.setUrl(url);
        view.setApplicationContext(applicationContext);
        ModelAndView modelAndView = new ModelAndView(view);
        List<EtiquetaProduto> list = new ArrayList<EtiquetaProduto>();
        list.add(etiquetaProdutos);
        modelAndView.addObject(list);

        return modelAndView;
    }
}
