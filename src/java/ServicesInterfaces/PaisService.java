/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.Pais;

/**
 *
 * @author Camilo
 */
public interface PaisService {
 
       abstract public ArrayList<Pais> getPaises();
       
       abstract public boolean createPais(String nombre,String iso);
       
       abstract public boolean updatePais(String pais_id, String nombre, String iso);
       
       abstract public boolean deletePais(String pais_id);     
    
    
}
