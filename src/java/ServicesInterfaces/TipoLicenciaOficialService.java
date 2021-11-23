/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoLicenciaOficial;

/**
 *
 * @author Camilo
 */
public interface TipoLicenciaOficialService {

    abstract public ArrayList<TipoLicenciaOficial> getTiposLicenciaOficial();

    abstract public boolean createTipoLicenciaOficial(String nombre, String pais_id);

    abstract public boolean deleteTipoLicenciaOficial(String id);

    abstract boolean updateTipoLicenciaOficialById(String id, String nombre, String pais_id);

}
