package br.com.etqpadrao.etqpadrao.controllers;


import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.models.Usuario;
import br.com.etqpadrao.etqpadrao.repository.ClienteRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Path;

@Controller
@RequestMapping("/produto")
@Transactional
public class ProdutoController {

    //Local onde o método irá salvar o arquivo do layout da etiquata em iReport.
    private final String LOCAL_SALVAMENTO =
            "C:\\Users\\lineh\\Documents\\Projetos em Andamento\\etqpadrao\\Project01\\src\\main\\resources\\report\\";

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;


    @PostMapping
    public ModelAndView save(@Valid Produto produto, @RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes, BindingResult result){

        if (result.hasErrors()){
            return form(produto);
        }
        Usuario userLog = usuarioRepo.veriricaLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        produto.setQmCadastrou(userLog.getLogin());
        produtoRepo.save(produto);
        //Local de salvamento do arquivo.
        String local = LOCAL_SALVAMENTO;
        produto.setEtq_layout(trata_nome(produto)+".jrxml");

        try {
            byte [] bytes = (file.getBytes());
            OutputStream outputStream = new FileOutputStream(local+produto.getEtq_layout());
            outputStream.write(bytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        produtoRepo.save(produtoRepo.findOne(produto.getId()));
        ModelAndView mv = new ModelAndView("redirect:/produto/form-input");
        mv.addObject(clienteRepo.findAll());
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso.");
        return mv;
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("produto/list");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public String viewReportJasper(@PathVariable("id") Long id){
        Produto pro=produtoRepo.findOne(id);
        String url ="classpath:report/"+pro.getEtq_layout();
        return url;
    }


    @GetMapping("/form-input")
    public ModelAndView form(Produto produto){
        ModelAndView modelAndView = new ModelAndView("produto/form-input");
        modelAndView.addObject("clientes", clienteRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editProduto(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("produto/form-edit");
        modelAndView.addObject("produto", produtoRepo.findOne(id));
        modelAndView.addObject("clientes", clienteRepo.findAll());
        return modelAndView;
    }

    /*
     * Tratamento do nome para ser salvo o layout da etiqueta.
     */
    private String trata_nome(Produto produto){
        String nome2 = produto.getNome_produto();
        String nome = Long.toString(produto.getId());
        if (nome2.length()>1) {
            String [] nome3 = nome2.split(" ");
            for (int i = 0; i < nome3.length; i++) {
                nome = nome + "_" + nome3[i];
            }
        } else {
            nome=nome+"_"+nome2;
        }
        return nome.toLowerCase();
    }
}
