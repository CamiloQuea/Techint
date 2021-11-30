/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;
import java.util.ArrayList;
import model.ConductorVehiculo;
/**
 *
 * @author Camilo
 */
public interface ConductorVehiculoService {
    
       abstract public ArrayList<ConductorVehiculo> getVehiculosConductores();
       
       abstract public boolean createVehiculoConductor(String conductor_id,String vehiculo_id);

       
       abstract public boolean deleteVehiculoConductor(String conductor_id,String vehiculo_id);     
    
}
