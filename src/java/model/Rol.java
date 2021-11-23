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
public class Rol {
    
    String rol_id;
    
    String rol_name;
    
    
    public Rol(String rol_id, String rol_name) {
        this.rol_id = rol_id;
        this.rol_name = rol_name;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
    }
    
    
    
}
