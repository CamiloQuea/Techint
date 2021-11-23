/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoInfraccion;

/**
 *
 * @author Camilo
 */
public interface TipoInfraccionService {

    abstract public ArrayList<TipoInfraccion> getTipoInfraccion();

    abstract public boolean createTipoInfraccion(String codigo,String nombre);

    abstract public boolean updateTipoInfraccion(String id,String codigo,String descripcion);

    abstract public boolean deleteTipoInfraccion(String tipoVehiculo_id);

}
