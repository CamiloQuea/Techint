/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;
import java.util.ArrayList;
import model.ConductorProyecto;
/**
 *
 * @author Camilo
 */
public interface ConductorProyectoService {
    
       abstract public ArrayList<ConductorProyecto> getProyectosConductores();
       
       abstract public boolean createProyectoConductor(String conductor_id,String proyecto_id);

       abstract public boolean deleteProyectoConductor(String conductor_id,String proyecto_id);     
    
}
