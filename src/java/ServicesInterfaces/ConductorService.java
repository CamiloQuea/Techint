/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;
import java.util.ArrayList;
import model.Conductor;
/**
 *
 * @author Camilo
 */
public interface ConductorService {
    
       abstract public ArrayList<Conductor> getConductores();
       
       abstract public boolean createConductor(String empresa_id,String gruposanguineo_id,String tipoDocumentoIdentidad_id,String pais_id,String conductor_documento,String conductor_nombre,String conductor_apellido,Boolean activo);
       
       abstract public boolean updateConductor(String id,String empresa_id,String gruposanguineo_id,String tipoDocumentoIdentidad_id,String pais_id,String conductor_documento,String conductor_nombre,String conductor_apellido,Boolean activo);
       
       abstract public boolean deleteConductor(String id);     
    
}
