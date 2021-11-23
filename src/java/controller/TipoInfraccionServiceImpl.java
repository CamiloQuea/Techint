/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import ServicesInterfaces.TipoInfraccionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.TipoInfraccion;
import model.TipoVehiculo;
import util.connection;

/**
 *
 * @author Camilo
 */
public class TipoInfraccionServiceImpl implements TipoInfraccionService {

    @Override
    public ArrayList<TipoInfraccion> getTipoInfraccion() {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from tipo_infraccion");

            ResultSet rs = statement.executeQuery();

            ArrayList<TipoInfraccion> tipoInfraccionList = new ArrayList<TipoInfraccion>();

            while (rs.next()) {

                TipoInfraccion tipoInfraccion = new TipoInfraccion(rs.getString(1), rs.getString(2), rs.getString(3));

                tipoInfraccionList.add(tipoInfraccion);

            }

            conn.close();

            return tipoInfraccionList;
        } catch (Exception e) {

            throw new Error(e);

        }

    }

    @Override
    public boolean createTipoInfraccion(String nombre, String detalle) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into tipo_infraccion (tipoInfraccion_id,tipoInfraccion_nombre,tipoInfraccion_detalle) values ( (select newTipoInfraccion_id()),?, ?)");

            ps.setString(1, nombre);
            ps.setString(2, detalle);

            int inserted = ps.executeUpdate();

            conn.close();

            if (inserted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Error(e);
        }

    }

    @Override
    public boolean updateTipoInfraccion(String id,String nombre, String detalle) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE tipo_infraccion SET tipoInfraccion_nombre=?,tipoInfraccion_detalle=? where tipoInfraccion_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, detalle);  
            ps.setString(3, id);
            
            int updated = ps.executeUpdate();

            conn.close();

            if (updated > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            throw new Error(e);
        }

    }

    @Override
    public boolean deleteTipoInfraccion(String tipoInfraccion_id) throws Error {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from tipo_infraccion where tipoInfraccion_id = ?");
            ps.setString(1, tipoInfraccion_id);
            int deleted = ps.executeUpdate();

            conn.close();

            if (deleted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

            throw new Error(e);

        }

    }

}
