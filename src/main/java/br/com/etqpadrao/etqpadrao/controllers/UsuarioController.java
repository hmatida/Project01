package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.CategoriaUsuario;
import br.com.etqpadrao.etqpadrao.models.Usuario;
import br.com.etqpadrao.etqpadrao.repository.CategoriaUsuarioRepo;
import br.com.etqpadrao.etqpadrao.repository.PermissionsRepo;
import br.com.etqpadrao.etqpadrao.repository.UsuarioRepo;
import br.com.etqpadrao.etqpadrao.utilidades.Inicia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private String PASSWORD = null;
    private Usuario passUser = new Usuario();

    @Autowired
    private CategoriaUsuarioRepo catUserRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PermissionsRepo permissionsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/form-input")
    public ModelAndView form(Usuario usuario){
        ModelAndView modelAndView = new ModelAndView("usuario/form-input");
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Usuario user, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()){
            return form(user);
        } else if(usuarioRepo.veriricaLogin(user.getLogin()) != null){
            ModelAndView modelAndView = new ModelAndView("redirect:/usuario/form-input");
            attributes.addFlashAttribute("erro", "O nome de login já existe. Escolha outro.");
            return modelAndView;
        }else {
            Inicia init = new Inicia();
            user.setAprovado("Não");
            Integer number = 1; //1 -> Nível de operador.
            Long setCatUser = number.longValue();
            user.setCat_usuario(catUserRepo.findOne(setCatUser));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            usuarioRepo.save(user);
            ModelAndView modelAndView = new ModelAndView("redirect:/usuario/form-input");
            attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso.");
            return modelAndView;
        }
    }

    @GetMapping("/cad-cat-usuario")
    public ModelAndView cadCatUsuario(Model model){
        model.addAttribute("catUsuario", new CategoriaUsuario());
        ModelAndView modelAndView = new ModelAndView("/usuario/user-function-input");
        modelAndView.addObject("permissao", permissionsRepo.findAll());
        return modelAndView;
    }

    @PostMapping("/save-cat")
    public ModelAndView saveCatUser(@Valid CategoriaUsuario categoriaUsuario, RedirectAttributes attributes){
        catUserRepo.save(categoriaUsuario);
        attributes.addFlashAttribute("mensagem", "Função de usuário cadastrado com sucesso.");
        return new ModelAndView("redirect:/usuario/cad-cat-usuario");
    }

    @GetMapping("/list-user")
    public ModelAndView listUser(){
        ModelAndView modelAndView = new ModelAndView("/usuario/list-user");
        modelAndView.addObject("usuarios", usuarioRepo.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/usuario/form-edit");
        modelAndView.addObject("usuario", usuarioRepo.findOne(id));
        PASSWORD = usuarioRepo.findOne(id).getPassword();
        passUser=usuarioRepo.findOne(id);
        modelAndView.addObject("nivel", catUserRepo.findAll());
        return modelAndView;
    }

    @PostMapping("/edit-save/{id}")
    public ModelAndView editSave(@PathVariable("id") Long id, @Valid Usuario user, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario/list-user");
        user.setId(id);
        user.setPassword(PASSWORD);
        user.setLogin(passUser.getLogin());
        usuarioRepo.save(user);
        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso.");
        return modelAndView;
    }
    @GetMapping("/edit-senha/{id}")
    public ModelAndView editSenha(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/usuario/edit-senha");
        passUser=usuarioRepo.findOne(id);
        modelAndView.addObject("usuario", usuarioRepo.findOne(id));
        modelAndView.addObject("nivel", catUserRepo.findAll());
        return modelAndView;
    }

    @PostMapping("/senha-save/{id}")
    public ModelAndView senhaSave(@PathVariable("id") Long id, @Valid Usuario user, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/usuario/list-user");
        user.setId(id);
        user.setLogin(passUser.getLogin());
        user.setNome(passUser.getNome());
        user.setCat_usuario(passUser.getCat_usuario());
        user.setEmail(passUser.getEmail());
        user.setAprovado(passUser.getAprovado());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usuarioRepo.save(user);
        attributes.addFlashAttribute("mensagem", "Senha do usuário salva com sucesso.");
        return modelAndView;
    }
}
