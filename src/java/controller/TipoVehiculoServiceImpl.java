/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import ServicesInterfaces.TipoVehiculoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.TipoVehiculo;
import util.connection;

/**
 *
 * @author Camilo
 */
public class TipoVehiculoServiceImpl implements TipoVehiculoService {

    @Override
    public ArrayList<TipoVehiculo> getTipoVehiculos() {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from tipo_vehiculo");

            ResultSet rs = statement.executeQuery();

            ArrayList<TipoVehiculo> tipoVehiculoList = new ArrayList<TipoVehiculo>();

            while (rs.next()) {

                TipoVehiculo tipoVehiculo = new TipoVehiculo(rs.getString(1), rs.getString(2), rs.getString(3));

                tipoVehiculoList.add(tipoVehiculo);

            }

            conn.close();

            return tipoVehiculoList;
        } catch (Exception e) {

            throw new Error(e);

        }

    }

    @Override
    public boolean createTipoVehiculo(String codigo, String detalle) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into tipo_vehiculo (tipoVehiculo_id,tipoVehiculo_cod,tipoVehiculo_detalle) values ( (select newTipoVehiculo_id()),?, ?)");

            ps.setString(1, codigo);
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
    public boolean updateTipoVehiculo(String id,String codigo, String detalle) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE tipo_vehiculo SET tipoVehiculo_cod=?,tipoVehiculo_detalle=? where tipoVehiculo_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, codigo);
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
    public boolean deleteTipoVehiculo(String tipoVehiculo_id) throws Error {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from tipo_vehiculo where tipoVehiculo_id = ?");
            ps.setString(1, tipoVehiculo_id);
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
