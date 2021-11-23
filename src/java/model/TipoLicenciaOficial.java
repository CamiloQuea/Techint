/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Camilo
 */
public class TipoLicenciaOficial {

    String id;
    String name;
    String pais_id;
    String pais_name;

    public TipoLicenciaOficial(String id, String name, String pais_id, String pais_name) {
        this.id = id;
        this.name = name;
        this.pais_id = pais_id;
        this.pais_name = pais_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPais_id() {
        return pais_id;
    }

    public void setPais_id(String pais_id) {
        this.pais_id = pais_id;
    }

    public String getPais_name() {
        return pais_name;
    }

    public void setPais_name(String pais_name) {
        this.pais_name = pais_name;
    }
    
    
    

}
