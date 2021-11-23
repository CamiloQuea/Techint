/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Camilo
 */
public class Conductor {
    private String id;
    private String documento;
    private String nombre;
    private String apellido;
    private Boolean activo;
    private String empresa_id;
    private String empresa_nombre;
    private String grupoSanguineo_id;
    private String grupoSanguineo_nombre;
    private String tipoDocumentoIdentidad_id;
    private String tipoDocumentoIdentidad_nombre;
    private String pais_id;
    private String pais_nombre;

    public Conductor(String id, String documento, String nombre, String apellido, Boolean activo, String empresa_id, String empresa_nombre, String grupoSanguineo_id, String grupoSanguineo_nombre, String tipoDocumentoIdentidad_id, String tipoDocumentoIdentidad_nombre, String pais_id, String pais_nombre) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = activo;
        this.empresa_id = empresa_id;
        this.empresa_nombre = empresa_nombre;
        this.grupoSanguineo_id = grupoSanguineo_id;
        this.grupoSanguineo_nombre = grupoSanguineo_nombre;
        this.tipoDocumentoIdentidad_id = tipoDocumentoIdentidad_id;
        this.tipoDocumentoIdentidad_nombre = tipoDocumentoIdentidad_nombre;
        this.pais_id = pais_id;
        this.pais_nombre = pais_nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(String empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getEmpresa_nombre() {
        return empresa_nombre;
    }

    public void setEmpresa_nombre(String empresa_nombre) {
        this.empresa_nombre = empresa_nombre;
    }

    public String getGrupoSanguineo_id() {
        return grupoSanguineo_id;
    }

    public void setGrupoSanguineo_id(String grupoSanguineo_id) {
        this.grupoSanguineo_id = grupoSanguineo_id;
    }

    public String getGrupoSanguineo_nombre() {
        return grupoSanguineo_nombre;
    }

    public void setGrupoSanguineo_nombre(String grupoSanguineo_nombre) {
        this.grupoSanguineo_nombre = grupoSanguineo_nombre;
    }

    public String getTipoDocumentoIdentidad_id() {
        return tipoDocumentoIdentidad_id;
    }

    public void setTipoDocumentoIdentidad_id(String tipoDocumentoIdentidad_id) {
        this.tipoDocumentoIdentidad_id = tipoDocumentoIdentidad_id;
    }

    public String getTipoDocumentoIdentidad_nombre() {
        return tipoDocumentoIdentidad_nombre;
    }

    public void setTipoDocumentoIdentidad_nombre(String tipoDocumentoIdentidad_nombre) {
        this.tipoDocumentoIdentidad_nombre = tipoDocumentoIdentidad_nombre;
    }

    public String getPais_id() {
        return pais_id;
    }

    public void setPais_id(String pais_id) {
        this.pais_id = pais_id;
    }

    public String getPais_nombre() {
        return pais_nombre;
    }

    public void setPais_nombre(String pais_nombre) {
        this.pais_nombre = pais_nombre;
    }
    
    
    
    
    
}
