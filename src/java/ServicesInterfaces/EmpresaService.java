/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;

import java.util.ArrayList;
import model.Empresa;

/**
 *
 * @author Camilo
 */
public interface EmpresaService {

    abstract public ArrayList<Empresa> getEmpresas();

    abstract public boolean createEmpresa(String ruc, String nombre);

    abstract public boolean deleteEmpresa(String rol_id);

    abstract boolean updateEmpresaById(String rol_id, String ruc, String nombre);

}
