package br.com.etqpadrao.etqpadrao.controllers;


import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.repository.ClienteRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private ClienteRepo clienteRepo;


    @PostMapping
    public ModelAndView save(@Valid Produto produto, @RequestParam MultipartFile file,
                             RedirectAttributes redirectAttributes){
        produtoRepo.save(produto);
        //Local de salvamento do arquivo.
        String local = "C:\\Users\\lineh\\Documents\\Projetos em Andamento\\etqpadrao\\Project01\\src\\main\\resources\\report\\";
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

//        // Add flash message for success
//        redirectAttributes.addFlashAttribute("flash",new FlashMessage("GIF successfully uploaded!", FlashMessage.Status.SUCCESS));
        return form();
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("produto/list");
        modelAndView.addObject("produtos", produtoRepo.findAll());
        return modelAndView;
    }


    @GetMapping("/form-input")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("produto/form-input");
        modelAndView.addObject("clientes", clienteRepo.findAll());
        return modelAndView;
    }

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
