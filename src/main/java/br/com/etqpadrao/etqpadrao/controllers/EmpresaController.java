package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.Empresa;
import br.com.etqpadrao.etqpadrao.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/empresa")
@Transactional
public class EmpresaController {

    @Autowired
    private EmpresaRepo empresaRepo;

    @PostMapping
    public ModelAndView save(@Valid Empresa empresa){
        empresaRepo.save(empresa);
        return new ModelAndView("redirect:/empresa/cad_empresa");
    }

    @GetMapping("/form-input")
    public ModelAndView form_input(){
        return new ModelAndView("empresa/cad_empresa");
    }
}
