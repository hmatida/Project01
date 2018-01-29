package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.LoginLog;
import br.com.etqpadrao.etqpadrao.models.Usuario;
import br.com.etqpadrao.etqpadrao.repository.LoginLogRepository;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.UsuarioRepo;
import br.com.etqpadrao.etqpadrao.utilidades.ProdutoGerado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.GET;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private LoginLogRepository loginLogRepository;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @GetMapping("/")
    public ModelAndView inicio(){
        Usuario userLog = usuarioRepo.veriricaLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        LoginLog loginLog = new LoginLog();
        loginLog.setLogin(userLog.getLogin());
        loginLogRepository.save(loginLog);
        ModelAndView modelAndView = new ModelAndView("/index");
        List<ProdutoGerado> progerList = new ArrayList<>();
        List<ProdutoGerado> progerList2 = new ArrayList<>();
        progerList2.addAll(produtoRepo.listUltProdGerado());
        if (progerList2.size() >=10) {
            for (int i = 0; i < 10; i++) {
                progerList.add(progerList2.get(i));
            }
            modelAndView.addObject("produtos", progerList);
        } else {
            modelAndView.addObject("produtos", produtoRepo.listUltProdGerado());
        }
            modelAndView.addObject("user", userLog);
            return modelAndView;
    }

    @RequestMapping("/login")
    public String entrar(){
        return "login";
    }
}
