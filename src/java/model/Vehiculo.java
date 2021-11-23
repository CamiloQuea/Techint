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
public class Vehiculo {
    
    String id;
    String tipoVehiculo_id;
    String placa;

    public Vehiculo(String id, String tipoVehiculo_id, String placa) {
        this.id = id;
        this.tipoVehiculo_id = tipoVehiculo_id;
        this.placa = placa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoVehiculo_id() {
        return tipoVehiculo_id;
    }

    public void setTipoVehiculo_id(String tipoVehiculo_id) {
        this.tipoVehiculo_id = tipoVehiculo_id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    
    
}
