/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoLicenciaInterna;

/**
 *
 * @author Camilo
 */
public interface TipoLicenciaInternaService {

    abstract public ArrayList<TipoLicenciaInterna> getTiposLicenciaInterna();

    abstract public boolean createTipoLicenciaInterna(String nombre);

    abstract public boolean deleteTipoLicenciaInterna(String id);

    abstract boolean updateTipoLicenciaInternaById(String id, String nombre);

}
