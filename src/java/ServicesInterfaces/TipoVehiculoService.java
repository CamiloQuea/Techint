/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoVehiculo;

/**
 *
 * @author Camilo
 */
public interface TipoVehiculoService {

    abstract public ArrayList<TipoVehiculo> getTipoVehiculos();

    abstract public boolean createTipoVehiculo(String codigo,String nombre);

    abstract public boolean updateTipoVehiculo(String id,String codigo,String descripcion);

    abstract public boolean deleteTipoVehiculo(String tipoVehiculo_id);

}
