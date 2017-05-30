/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.controlehoras.dao.StatusDao;
import br.com.controlehoras.modelo.Status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class StatusTeste {
    @Test
    public void lerListaStatusOrdenada() {
        StatusDao statusDao = new StatusDao();
        List<Status> statusLista = new ArrayList<>();
        statusLista = statusDao.listarObjetosCriterios();

        for (Status status : statusLista) {
            System.out.println("-------------------------------------");
            System.out.println(status.toString());
            System.out.println("-------------------------------------");
        }

    }
}
