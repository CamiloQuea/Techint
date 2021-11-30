/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class ConductorVehiculo {
    private String conductor_id;
    private String conductor_nombre;
    private String conductor_apellido;
    private String vehiculo_id;
    private String vehiculo_placa;
    private String tipoVehiculo_id;
    private String tipoVehiculo_nombre;

    public ConductorVehiculo(String conductor_id, String conductor_nombre, String conductor_apellido, String vehiculo_id, String vehiculo_placa, String tipoVehiculo_id, String tipoVehiculo_nombre) {
        this.conductor_id = conductor_id;
        this.conductor_nombre = conductor_nombre;
        this.conductor_apellido = conductor_apellido;
        this.vehiculo_id = vehiculo_id;
        this.vehiculo_placa = vehiculo_placa;
        this.tipoVehiculo_id = tipoVehiculo_id;
        this.tipoVehiculo_nombre = tipoVehiculo_nombre;
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


    public String getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(String vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public String getVehiculo_placa() {
        return vehiculo_placa;
    }

    public void setVehiculo_placa(String vehiculo_placa) {
        this.vehiculo_placa = vehiculo_placa;
    }

    public String getTipoVehiculo_id() {
        return tipoVehiculo_id;
    }

    public void setTipoVehiculo_id(String tipoVehiculo_id) {
        this.tipoVehiculo_id = tipoVehiculo_id;
    }

    public String getTipoVehiculo_nombre() {
        return tipoVehiculo_nombre;
    }

    public void setTipoVehiculo_nombre(String tipoVehiculo_nombre) {
        this.tipoVehiculo_nombre = tipoVehiculo_nombre;
    }



    
    
    
}
