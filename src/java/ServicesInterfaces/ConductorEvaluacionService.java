/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.ConductorEvaluacion;

/**
 *
 * @author Camilo
 */
public interface ConductorEvaluacionService {

    abstract public ArrayList<ConductorEvaluacion> getEvaluacionConductores();

    abstract public boolean createEvaluacionConductor(String conductor_id, String vehiculo_id, Double notaExamTeo, Double notaExamPrac);

    abstract public boolean deleteEvaluacionConductor(String evaluacion_id);
    
    abstract public boolean updateEvaluacionConductor(String evaluacion_id,String conductor_id, String evaluador_id, Double notaExamTeo, Double notaExamPrac);

}
