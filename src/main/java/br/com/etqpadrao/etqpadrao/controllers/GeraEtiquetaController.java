package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.*;
import br.com.etqpadrao.etqpadrao.repository.EtiquetaProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.PassaDadosRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.UsuarioRepo;
import groovy.transform.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/etiqueta")
public class GeraEtiquetaController {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PassaDadosRepo passaDadosRepo;

    @Autowired
    private EtiquetaProdutoRepo etiquetaProdutoRepo;

    @PostMapping
    public ModelAndView geraEtiqueta(@Valid PassaDados passaDados, BindingResult result, RedirectAttributes attributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/etiqueta/form-input");
        Usuario userLog = usuarioRepo.veriricaLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (result.hasErrors()){
            return form(passaDados);
            /**
             * Esse método abaixo ainda precisa verificar o nível de permissão do usuário
             * para que o usuário supervisor ou acima, possa gerar mais etiquetas.*/
        } else if( veriricaSeFoiGeradaEtiquetaAntes(passaDados)== true){
            attributes.addFlashAttribute("erro", "Já foram geradas etiquetas com data posterior a essa fabricação. Não é possível erar etiqueta com data retroativa.");
            return modelAndView;
        }

        else {
            passaDados.setLogUserGeado(userLog.getLogin());
            passaDadosRepo.save(passaDados);
            Produto pro = produtoRepo.findOne(passaDados.getProduto().getId());

            /*
            * Verifica qual produto está sendo passado para se gerar a etiqueta.
            * */
            if (pro.getCod_produto().equals("PAC030")){
                for (int i = 0; i < passaDados.getNum_caixas(); i++) {
                    EtiquetaProduto etiquetaProduto = new EtiquetaProduto();

                    //Seta data de fabricação em etiquetaProduto
                    etiquetaProduto.setFabricacao(passaDados.getDt_fabricacao());

                    //Seta data de vencimento do produto em etiquetaProduto
                    Calendar venci = Calendar.getInstance();
                    venci.set(passaDados.getDt_fabricacao().get(Calendar.YEAR),
                            passaDados.getDt_fabricacao().get(Calendar.MONTH),
                            passaDados.getDt_fabricacao().get(Calendar.DAY_OF_MONTH));
                    venci.add(Calendar.MONTH, passaDados.getProduto().getValidade());
                    etiquetaProduto.setValidade(venci);

                    //Seta produto na etiqueta
                    etiquetaProduto.setProduto(pro);

                    //Seta lote
                    DataLote dataLote = new DataLote();
                    etiquetaProduto.setLote(dataLote.geraLote(passaDados.getDt_fabricacao()));

                    //Atualiza o número de caixas cujas etiquetas foram geradas e as salva no banco
                    pro.setNumero_caixa(pro.getNumero_caixa() + 1);
                    etiquetaProduto.setNum_caixa(pro.getNumero_caixa());
                    produtoRepo.save(pro);

                    //Seta Cod3
                    Code3 code3 = new Code3();
                    etiquetaProduto.setCod3(code3.geraCodeBP(passaDados));

                    //Seta
                    etiquetaProduto.setFab_string(passaDados.getDt_fabricacao());
                    etiquetaProduto.setVal_string(etiquetaProduto.getValidade());

                    //Seta usuário que gerou
                    etiquetaProduto.setLogUserGerado(userLog.getLogin());

                    etiquetaProdutoRepo.save(etiquetaProduto);
                }
                attributes.addFlashAttribute("mensagem", "Etiquetas geradas com sucesso.");
            } else if(pro.getCod_produto().equals("PAC131")){
                for (int i = 0; i < passaDados.getNum_caixas(); i++) {
                    EtiquetaProduto etiquetaProduto = new EtiquetaProduto();

                    //Seta data de fabricação em etiquetaProduto
                    etiquetaProduto.setFabricacao(passaDados.getDt_fabricacao());

                    //Seta data de vencimento do produto em etiquetaProduto
                    Calendar venci = Calendar.getInstance();
                    venci.set(passaDados.getDt_fabricacao().get(Calendar.YEAR),
                            passaDados.getDt_fabricacao().get(Calendar.MONTH),
                            passaDados.getDt_fabricacao().get(Calendar.DAY_OF_MONTH));
                    venci.add(Calendar.MONTH, passaDados.getProduto().getValidade());
                    etiquetaProduto.setValidade(venci);

                    //Seta produto na etiqueta
                    etiquetaProduto.setProduto(pro);

                    //Seta lote
                    DataLote dataLote = new DataLote();
                    etiquetaProduto.setLote(dataLote.geraLote(passaDados.getDt_fabricacao()));

                    //Atualiza o número de caixas cujas etiquetas foram geradas e as salva no banco
                    pro.setNumero_caixa(pro.getNumero_caixa() + 1);
                    etiquetaProduto.setNum_caixa(pro.getNumero_caixa());
                    produtoRepo.save(pro);

                    //Seta Cod3
                    Code3 code3 = new Code3();
                    etiquetaProduto.setCod3(code3.geraCodeBPPAC131(passaDados));

                    //Seta
                    etiquetaProduto.setFab_string(passaDados.getDt_fabricacao());
                    etiquetaProduto.setVal_string(etiquetaProduto.getValidade());

                    //Seta usuário que gerou
                    etiquetaProduto.setLogUserGerado(userLog.getLogin());
                    etiquetaProdutoRepo.save(etiquetaProduto);
                }
                attributes.addFlashAttribute("mensagem", "Etiquetas geradas com sucesso.");
            } else {
                attributes.addFlashAttribute("erro", "Não foi encontrado layout padrão de etiqueta para esse produto.");
            }
            return modelAndView;
        }
    }

    @GetMapping("/form-input")
    public ModelAndView form(PassaDados passaDados){
        ModelAndView modelAndView = new ModelAndView("etiqueta/gera_etiqueta");
        modelAndView.addObject("produtos",produtoRepo.findAll());
        return modelAndView;
    }
    /*
     *Método que verifica se já foi gerada alguma etiqueta para aquele dia.
     * Recebe como parâmetro uma calsse PassaDados
     * */
    private boolean verificaEtiquetaFoiGerada(PassaDados passaDados){
        PassaDados pd = passaDadosRepo
                .verificaSeGerouEtiqueta(passaDados.getDt_fabricacao(), passaDados.getProduto());
        if(pd == null){
            return false;
        } else {
            return true;
        }
    }

    /*
    * Recebe como parâmetro um objeto PassaDados e retorna true
    * se a data passada é mais recente de uma fabricação.
    * */
    private boolean veriricaSeFoiGeradaEtiquetaAntes(PassaDados passaDados){
        return passaDados.getDt_fabricacao()
                .before(passaDadosRepo.retornaDtFabricacaoMaisRecente(passaDados.getProduto()));

    }
}
