/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.controlehoras.dao.FuncionarioDao;
import br.com.controlehoras.modelo.Funcionario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class FuncionarioTeste {

    @Test
    public void lerListaFuncionarioOrdenada() {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios = funcionarioDao.listarObjetosCriterios();

        for (Funcionario funcionario : funcionarios) {
            System.out.println("-------------------------------------");
            System.out.println(funcionario.toString());
            System.out.println("-------------------------------------");
        }

    }

    @Test
    @Ignore
    public void buscarFuncionario() {
        FuncionarioDao c = new FuncionarioDao();
        Long codigo = 48267L;
        Funcionario objeto = c.buscarObjeto(codigo);
        System.out.println("-------------------------------------");
        System.out.println(objeto.toString());
        System.out.println("-------------------------------------");

    }

    @Test
    @Ignore
    public void cadastrar() {
        Funcionario objeto = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        objeto.setNome("Altobeli da Rocha");

        funcionarioDao.salvarOuAtualizarObjeto(objeto);
    }

    @Test
    @Ignore
    public void autenticarLogin() {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        //boolean resultado = funcionarioDao.autenticarLogin("rocha", "");
        //System.out.println(resultado);
    }

}
