package br.com.etqpadrao.etqpadrao.controllers;

import br.com.etqpadrao.etqpadrao.models.*;
import br.com.etqpadrao.etqpadrao.repository.EtiquetaProdutoRepo;
import br.com.etqpadrao.etqpadrao.repository.PassaDadosRepo;
import br.com.etqpadrao.etqpadrao.repository.ProdutoRepo;
import groovy.transform.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/gera_etq")
public class GeraEtiquetaController {

    @Autowired
    private ProdutoRepo produtoRepo;

    @Autowired
    private PassaDadosRepo passaDadosRepo;

    @Autowired
    private EtiquetaProdutoRepo etiquetaProdutoRepo;

    @PostMapping
    public ModelAndView geraEtiqueta(@Valid PassaDados passaDados){
        passaDadosRepo.save(passaDados);
        Produto pro = produtoRepo.findOne(passaDados.getProduto().getId());

        for (int i = 0; i<passaDados.getNum_caixas(); i++){
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
            pro.setNumero_caixa(pro.getNumero_caixa()+1);
            etiquetaProduto.setNum_caixa(pro.getNumero_caixa());
            produtoRepo.save(pro);

            //Seta Cod3
            Code3 code3=new Code3();
            etiquetaProduto.setCod3(code3.geraCodeBP(passaDados));

            //Seta
            etiquetaProduto.setFab_string(passaDados.getDt_fabricacao());
            etiquetaProduto.setVal_string(etiquetaProduto.getValidade());

            etiquetaProdutoRepo.save(etiquetaProduto);
        }
        return form();
    }

    @GetMapping("/form-input")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("etiqueta/gera_etiqueta");
        modelAndView.addObject("produtos",produtoRepo.findAll());
        return modelAndView;
    }
}
