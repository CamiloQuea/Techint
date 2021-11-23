package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import util.connection;

public class User {

    private String id;

    private String nombre;

    private String apellido;

    private String email;

    private String contraseña;

    private String rol_name;

    private String rol_id;

    public User(String id, String nombre, String apellido, String email, String rol_id, String rol_name) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.rol_name = rol_name;
        this.rol_id = rol_id;
    }

    public User(String id, String nombre, String apellido, String email, String rol_id, String rol_name, String password) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.rol_name = rol_name;
        this.rol_id = rol_id;
        this.contraseña = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol_name() {
        return rol_name;
    }

    public void setRol_name(String rol_name) {
        this.rol_name = rol_name;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {

        return (getNombre() + " " + getApellido());
        
    }

}
