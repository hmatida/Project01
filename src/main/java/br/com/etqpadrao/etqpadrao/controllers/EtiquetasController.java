package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.funcionais.CalendarForString;
import br.com.etqpadrao.etqpadrao.models.EtiquetaProduto;
import br.com.etqpadrao.etqpadrao.models.Filtros;
import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.models.Usuario;
import br.com.etqpadrao.etqpadrao.repository.EtiquetaProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.UsuarioRepo;
import br.com.etqpadrao.etqpadrao.utilidades.LongAndString;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.core.Application;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

import static net.sf.jasperreports.engine.JasperFillManager.*;

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

    @Autowired
    private UsuarioRepo usuarioRepo;

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

    @GetMapping("/cancalamentoetiqueta/{id}")
    public ModelAndView cancalamentoDeEtiqueta(@PathVariable("id") Long id, Model model){
        model.addAttribute("transfer", new LongAndString());
        EtiquetaProduto etqPro = new EtiquetaProduto();
        etqPro=etiquetaProdutoRepo.findOne(id);
        ModelAndView modelAndView = new ModelAndView("etiqueta/cancela-etiqueta");
        modelAndView.addObject("etqpro", etqPro);
        return modelAndView;
    }

    @GetMapping("/detalheetiqueta/{id}")
    public ModelAndView cancalamentoDeEtiqueta(@PathVariable("id") Long id){
        EtiquetaProduto etqPro = etiquetaProdutoRepo.findOne(id);
        ModelAndView modelAndView = new ModelAndView("etiqueta/detalhe-etiqueta");
        modelAndView.addObject("etqpro", etqPro);
        return modelAndView;
    }

    @PostMapping("/cancela/{id}")
    public ModelAndView cancelaEtiqueta(@PathVariable("id") Long id, @Valid LongAndString transfer, RedirectAttributes attributes){
        Usuario userLog = usuarioRepo.veriricaLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        EtiquetaProduto etq = etiquetaProdutoRepo.findOne(id);
        etq.setAtiva("Cancelada");
        Calendar hj = Calendar.getInstance();
        etq.setMotivoCancelamento(transfer.getDescription());
        etq.setDtCancelamento(hj);
        etq.setLogUserCancelou(userLog.getLogin());
        etiquetaProdutoRepo.save(etq);
        ModelAndView modelAndView = new ModelAndView("redirect:/etiquetas/list");
        attributes.addFlashAttribute("mensagem", "Etiqueta cancelada com sucesso");
        return modelAndView;
    }

    @PostMapping("/list_etq")
    public ModelAndView list_etiquetas_completo(@Valid Filtros filtros, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("etiqueta/list_etiquetas");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        if (filtros.getDt_inicial() != null && filtros.getDt_final()!=null){
            System.out.println("entrou if 1......");
            modelAndView.addObject("etiquetas", etiquetaProdutoRepo.findByDataIntervalAll(filtros.getDt_inicial(),
                    filtros.getDt_final(), filtros.getProduto()));
        } else if(filtros.getCx_inicial() >= 0 && filtros.getCx_final() >0){
            System.out.println("entrou if 2......");
            modelAndView.addObject("etiquetas", etiquetaProdutoRepo.findByBoxIntervalAll(filtros.getCx_inicial(),
                    filtros.getCx_final(), filtros.getProduto()));
        } else if(filtros.getLote() != ""){
            System.out.println("entrou if 3......");
            modelAndView.addObject("etiquetas", etiquetaProdutoRepo.findByLoteAll(filtros.getLote(), filtros.getProduto()));
        } else {
            System.out.println("entrou else......");
            modelAndView.setViewName("redirect:/etiquetas/list");
            attributes.addFlashAttribute("erro", "Algum dos campos a serem buscados não foi preenchido corretamente.");
        }
        return modelAndView;
    }

    @PostMapping("/filtro_1")
    public ModelAndView filtro1_1(@Valid Filtros filtros) {
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

    /*
    * Método que enviar as etiquetas direto para a impressora.
    * */
    private void imprimeEtiqueta(List<EtiquetaProduto> etiquetaProdutos, Produto produto) throws JRException {
        Map map = new HashMap();
        for (int i =0; i<etiquetaProdutos.size(); i++){
            map.put(i, etiquetaProdutos.get(i));
        }
        String url="classpath:report/"+produto.getEtq_layout();
        JasperReport report = JasperCompileManager.compileReport("C:\\Users\\lineh\\Documents\\Projetos em Andamento\\etqpadrao\\Project01\\src\\main\\resources\\report\\"+produto.getEtq_layout());
        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(etiquetaProdutos));
        //Como se trata de um sistema web, a chamada é feita no servidor de deploy da aplicação.
        JasperPrintManager.printReport(print, false);
    }

    //Método teste
//    @PostMapping("/filtro_1")
    public void testaImpressão(@Valid Filtros filtros) throws JRException {
        imprimeEtiqueta(etiquetaProdutoRepo.findByDataInterval(filtros.getDt_inicial(), filtros.getDt_final(),
                filtros.getProduto()), filtros.getProduto());
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
