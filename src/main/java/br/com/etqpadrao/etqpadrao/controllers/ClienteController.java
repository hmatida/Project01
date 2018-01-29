package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.Cliente;
import br.com.etqpadrao.etqpadrao.models.Empresa;
import br.com.etqpadrao.etqpadrao.repository.ClienteRepo;
import br.com.etqpadrao.etqpadrao.repository.EmpresaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.GET;

@Controller
@RequestMapping("/cliente")
@Transactional
public class ClienteController {

    @Autowired
    private ClienteRepo clienteRepo;

    @Autowired
    private EmpresaRepo empresaRepo;

    @PostMapping
    public ModelAndView save(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return form(cliente);
        }
        else {
            clienteRepo.save(cliente);
            ModelAndView modelAndView = new ModelAndView("redirect:/cliente/form-input");
            modelAndView.addObject(empresaRepo.findAll());
            attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso.");
            return modelAndView;
        }
    }

    @GetMapping("/form-input")
    public ModelAndView form(Cliente cliente){
        ModelAndView modelAndView = new ModelAndView("cliente/form-input");
        modelAndView.addObject("empresas", empresaRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("cliente/list");
        modelAndView.addObject("clientes", clienteRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("cliente/form-edit");
        modelAndView.addObject("cliente", clienteRepo.findOne(id));
        modelAndView.addObject("empresas", empresaRepo.findAll());
        return modelAndView;
    }
    @PostMapping("/edit-save/{id}")
    public ModelAndView editSave(@Valid Cliente cliente, @PathVariable("id") Long id){
        cliente.setId(id);
        clienteRepo.save(cliente);
        return list();
    }
}
