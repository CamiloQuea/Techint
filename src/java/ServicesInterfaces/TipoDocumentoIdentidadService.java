/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoDocumentoIdentidad;

/**
 *
 * @author Camilo
 */
public interface TipoDocumentoIdentidadService {

    abstract public ArrayList<TipoDocumentoIdentidad> getTiposDeDocumentoDeIdentidad();

    abstract public boolean createTipoDeDocumentoDeIdentidad(String nombre);

    abstract public boolean deleteTipoDeDocumentoDeIdentidad(String rol_id);

    abstract boolean updateTipoDeDocumentoDeIdentidadById(String rol_id, String nombre);

}
