/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.Rol;

/**
 *
 * @author Camilo
 */
public interface RolService {

    abstract public ArrayList<Rol> getRols();

    abstract public boolean createRol(String nombre);

    abstract public boolean deleteRol(String rol_id);

    abstract boolean updateRolById(String rol_id, String nombre);

}
