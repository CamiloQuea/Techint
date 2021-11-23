/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.GrupoSanguineo;

/**
 *
 * @author Camilo
 */
public interface GrupoSanguineoService {

    abstract public ArrayList<GrupoSanguineo> getGrupoSanguineo();

    abstract public boolean createGrupoSanguineo(String nombre);

    abstract public boolean deleteGrupoSanguineo(String rol_id);

    abstract boolean updateGrupoSanguineoById(String rol_id, String nombre);

}
