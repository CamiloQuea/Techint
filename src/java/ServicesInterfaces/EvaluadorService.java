/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.Evaluador;

/**
 *
 * @author Camilo
 */
public interface EvaluadorService {

    abstract public ArrayList<Evaluador> getEvaluadores();

    abstract public boolean createEvaluador(String empresa_id, String nombre, String apellido);

    abstract public boolean deleteEvaluador(String id);

    abstract public boolean updateEvaluador(String id, String empresa_id, String nombre, String apellido);

}
