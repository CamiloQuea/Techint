/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class ConductorProyecto {

    private String conductor_id;
    private String conductor_nombre;
    private String conductor_apellido;
    private String proyecto_id;
    private String proyecto_codigo;
    private String proyecto_nombre;

    public ConductorProyecto(String conductor_id, String conductor_nombre, String conductor_apellido, String proyecto_id, String proyecto_codigo, String proyecto_nombre) {
        this.conductor_id = conductor_id;
        this.conductor_nombre = conductor_nombre;
        this.conductor_apellido = conductor_apellido;
        this.proyecto_id = proyecto_id;
        this.proyecto_codigo = proyecto_codigo;
        this.proyecto_nombre = proyecto_nombre;
    }

    public String getConductor_id() {
        return conductor_id;
    }

    public void setConductor_id(String conductor_id) {
        this.conductor_id = conductor_id;
    }

    public String getConductor_nombre() {
        return conductor_nombre;
    }

    public void setConductor_nombre(String conductor_nombre) {
        this.conductor_nombre = conductor_nombre;
    }

    public String getConductor_apellido() {
        return conductor_apellido;
    }

    public void setConductor_apellido(String conductor_apellido) {
        this.conductor_apellido = conductor_apellido;
    }

    public String getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(String proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public String getProyecto_codigo() {
        return proyecto_codigo;
    }

    public void setProyecto_codigo(String proyecto_codigo) {
        this.proyecto_codigo = proyecto_codigo;
    }

    public String getProyecto_nombre() {
        return proyecto_nombre;
    }

    public void setProyecto_nombre(String proyecto_nombre) {
        this.proyecto_nombre = proyecto_nombre;
    }

}
