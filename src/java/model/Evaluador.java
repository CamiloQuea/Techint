/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class Evaluador {
    private String id;
    private String nombre;
    private String apellido;
    private String empresa_id;
    private String empresa_name;

    public Evaluador(String id, String nombre, String apellido, String empresa_id, String empresa_name) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.empresa_id = empresa_id;
        this.empresa_name = empresa_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(String empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getEmpresa_name() {
        return empresa_name;
    }

    public void setEmpresa_name(String empresa_name) {
        this.empresa_name = empresa_name;
    }

    
    
    
    
}
