package br.com.etqpadrao.etqpadrao.controllers;


import br.com.etqpadrao.etqpadrao.models.Produto;
import br.com.etqpadrao.etqpadrao.repository.ClienteRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/produto")
@Transactional
public class ProdutoController {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private ClienteRepo clienteRepo;

    @PostMapping
    public ModelAndView save(@Valid Produto produto){
        produtoRepo.save(produto);
        return form();
    }

    @GetMapping("/form-input")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("produto/form-input");
        modelAndView.addObject("clientes", clienteRepo.findAll());
        return modelAndView;
    }
}
