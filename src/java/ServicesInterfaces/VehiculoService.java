/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.TipoVehiculo;
import model.Vehiculo;

/**
 *
 * @author Camilo
 */
public interface VehiculoService {

    abstract public ArrayList<Vehiculo> getVehiculos();

    abstract public boolean createVehiculo(String tipoVehiculo_id, String placa);

    abstract public boolean updateVehiculo(String id, String tipoVehiculo_id, String placa);

    abstract public boolean deleteVehiculo(String tipoVehiculo_id);
}
