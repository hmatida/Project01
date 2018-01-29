package br.com.etqpadrao.etqpadrao.utilidades;

import br.com.etqpadrao.etqpadrao.models.CategoriaUsuario;
import br.com.etqpadrao.etqpadrao.models.Permissions;
import br.com.etqpadrao.etqpadrao.repository.CategoriaUsuarioRepo;
import br.com.etqpadrao.etqpadrao.repository.PermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class Inicia {

    @Autowired
    private CategoriaUsuarioRepo categoriaUsuarioRepo;

    @Autowired
    private PermissionsRepo permissionsRepo;

    public void inicia(){
        lanca();
        if (categoriaUsuarioRepo.findAll() == null){
            lanca();
            System.out.println("Inicialização dos processos OK.....");
        }
        else{
            System.out.println("Não foi necessário lanças as funções  de usuários...");
        }
    }

    public void lancaPermission(){
        Permissions per1 = new Permissions();
        per1.setPermission("ROLE_1");
        Integer number = 1;
        per1.setId(number.longValue());
        permissionsRepo.save(per1);
        per1.setPermission("ROLE_2");
        number = 2;
        per1.setId(number.longValue());
        permissionsRepo.save(per1);
        per1.setPermission("ROLE_3");
        number = 3;
        per1.setId(number.longValue());
        permissionsRepo.save(per1);
        per1.setPermission("ROLE_1.1");
        number = 4;
        per1.setId(number.longValue());
        permissionsRepo.save(per1);


    }

    private void lanca(){
        CategoriaUsuario cat1 = new CategoriaUsuario();
        CategoriaUsuario cat2 = new CategoriaUsuario();
        CategoriaUsuario cat3 = new CategoriaUsuario();

        cat1.setNome("Administrador");
        categoriaUsuarioRepo.save(cat1);
        cat2.setNome("Supervisor");
        categoriaUsuarioRepo.save(cat2);
        cat3.setNome("Usuário");
        categoriaUsuarioRepo.save(cat3);
    }
}
