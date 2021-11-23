/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.Proyecto;

/**
 *
 * @author Camilo
 */
public interface ProyectoService {

    abstract public ArrayList<Proyecto> getProyectos();

    abstract public boolean createProyecto(String codigo, String usuario_id, String nombre);

    abstract public boolean updateProyecto(String id, String codigo, String usuario_id, String nombre);

    abstract public boolean deleteProyecto(String tipoVehiculo_id);

}
