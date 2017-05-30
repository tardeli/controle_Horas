package Teste;

import br.com.controlehoras.dao.EscalaDao;
import br.com.controlehoras.modelo.Escala;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class EscalaTeste {
    
    EscalaDao escalaDao = new EscalaDao();
    @Ignore
    public Date converterString_Data(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(data); // Ex.: "01/01/2014 10:00:00"
        return date;
    }
   
    @Ignore
    public void testarDta(){
        boolean re = escalaDao.pesquisarData(new Date());
        System.out.println(re);
    }
    
    @Test
    public void teste(){
       
        Escala escala = escalaDao.ultimaDataEscala();
        System.out.println("resultado: "+escala.getData());
    }
    
    @Ignore
    public void salvar() throws ParseException {
        EscalaDao escalaDao = new EscalaDao();
        boolean re = escalaDao.pesquisarData(converterString_Data("18/01/2017"));
        System.out.println("resultado: "+re);
    }
}
