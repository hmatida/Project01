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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
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
    public ModelAndView save(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()) {
            return form_input(empresa);
        } else {
            empresaRepo.save(empresa);
            ModelAndView modelAndView = new ModelAndView("redirect:/empresa/cad_empresa");
            attributes.addFlashAttribute("mensagem", "Empresa salva com sucesso.");
            return modelAndView;
        }
    }

    @GetMapping("/form-input")
    public ModelAndView form_input(Empresa empresa){
        return new ModelAndView("empresa/cad_empresa");
    }
}
