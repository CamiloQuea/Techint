/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.VehiculoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Vehiculo;
import util.connection;

/**
 *
 * @author Camilo
 */
public class VehiculoServiceImpl implements VehiculoService {

    @Override
    public ArrayList<Vehiculo> getVehiculos() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from vehiculo");

            ResultSet rs = statement.executeQuery();

            ArrayList<Vehiculo> vehiculoList = new ArrayList<Vehiculo>();

            while (rs.next()) {

                Vehiculo vehiculo = new Vehiculo(rs.getString(1), rs.getString(2), rs.getString(3));

                vehiculoList.add(vehiculo);

            }

            conn.close();

            return vehiculoList;
        } catch (Exception e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createVehiculo(String tipoVehiculo_id, String placa) {
        try {

            System.out.println(tipoVehiculo_id + "----------" + placa + "+++++++++");

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into vehiculo (vehiculo_id,tipoVehiculo_id,vehiculo_placa) values ( (select newVehiculo_id()),?, ?)");

            ps.setString(1, tipoVehiculo_id);
            ps.setString(2, placa);

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
    public boolean updateVehiculo(String id, String tipoVehiculo_id, String placa) {
        try {

            System.out.println(id + " "
                    + tipoVehiculo_id + " "
                    + placa + "");

            Connection conn = connection.getConnection();

            String sql = "UPDATE vehiculo SET tipoVehiculo_id=?,vehiculo_placa=? where vehiculo_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, tipoVehiculo_id);
            ps.setString(2, placa);
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
    public boolean deleteVehiculo(String vehiculo_id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from vehiculo where vehiculo_id = ?");
            ps.setString(1, vehiculo_id);
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
