/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.controlehoras.dao.TurnoDao;
import br.com.controlehoras.modelo.Turno;
import java.util.Collection;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class TurnoTeste {

    @Test
    public void mostrarRegistros() {
        TurnoDao turnoDao = new TurnoDao();
        List<Turno> turnos = turnoDao.listarObjetos();

        System.out.println("-----------------");
        for (int i = 0; i < turnos.size(); i++) {
            System.out.println(turnos.get(i).getCodigo().toString());
            System.out.println(turnos.get(i).getNome());
            System.out.println(turnos.get(i).getHoraInicio());
            System.out.println(turnos.get(i).getHoraFinal());
            System.out.println(turnos.get(i).getHorasTrabalhadas());

//            for (int j = 0; j < turnos.get(i).getFuncionarios().size() ; j++) {
//                System.out.println(turnos.get(i).getFuncionarios().get(j).getMatricula());
//                
//            }

            System.out.println("-----------------");
        }

    }
}
