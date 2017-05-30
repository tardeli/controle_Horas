/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlehoras.converter;

import br.com.controlehoras.dao.FuncionarioDao;
import br.com.controlehoras.modelo.Funcionario;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Tardeli
 */
@FacesConverter(value = "funcionarioPickListConverter")
public class funcionarioPickListConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            Funcionario objeto = funcionarioDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Funcionario funcionario = (Funcionario) value;
            Long codigo = funcionario.getMatricula();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }

}
