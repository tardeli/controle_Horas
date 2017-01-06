package br.com.controlehoras.dao;

import br.com.controlehoras.modelo.ItemEscala;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Tardeli
 */
public class ItemEscalaDao extends Generic_Dao<ItemEscala> implements Serializable{
    public List<ItemEscala> buscarItensPedido(Date data) {

        List<ItemEscala> listaTemporaria = listarObjetos();
        List<ItemEscala> itensPedido = new ArrayList<>();

        try {
            for (ItemEscala itemEscala : listaTemporaria) {
                if (itemEscala.getEscala().getData().equals(data)) {
                    itensPedido.add(itemEscala);
                }
            }
            return itensPedido;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
