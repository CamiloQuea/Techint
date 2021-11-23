/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class Pais {
    
    private String pais_id;
    private String pais_iso;
    private String pais_nombre;

    public Pais(String pais_id, String pais_iso, String pais_nombre) {
        this.pais_id = pais_id;
        this.pais_iso = pais_iso;
        this.pais_nombre = pais_nombre;
    }

    public String getPais_id() {
        return pais_id;
    }

    public void setPais_id(String pais_id) {
        this.pais_id = pais_id;
    }

    public String getPais_iso() {
        return pais_iso;
    }

    public void setPais_iso(String pais_iso) {
        this.pais_iso = pais_iso;
    }

    public String getPais_nombre() {
        return pais_nombre;
    }

    public void setPais_nombre(String pais_nombre) {
        this.pais_nombre = pais_nombre;
    }
    
    
    
}
