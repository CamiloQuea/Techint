/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.ConductorVehiculoService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;
import util.connection;
import java.sql.Connection;
import java.sql.SQLException;

import model.ConductorVehiculo;

/**
 *
 * @author Camilo
 */
public class ConductorVehiculoServiceImpl implements ConductorVehiculoService {

    @Override
    public ArrayList<ConductorVehiculo> getVehiculosConductores() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("SELECT c.conductor_id,c.conductor_nombre,"
                            + "c.conductor_apellido,v.vehiculo_id,v.vehiculo_placa,"
                            + "v.tipoVehiculo_id,tv.tipoVehiculo_detalle FROM "
                            + "vehiculo_conductor vc "
                            + "INNER JOIN vehiculo v on v.vehiculo_id=vc.vehiculo_id "
                            + "INNER JOIN conductor c on vc.vehiculo_id=v.vehiculo_id "
                            + "INNER JOIN tipo_vehiculo tv on tv.tipoVehiculo_id=v.tipoVehiculo_id");

            ResultSet rs = statement.executeQuery();

            ArrayList<ConductorVehiculo> conductorVehiculolist = new ArrayList();

            while (rs.next()) {

                ConductorVehiculo vehiculoconductor = new ConductorVehiculo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                );

                conductorVehiculolist.add(vehiculoconductor);

            }

            conn.close();

            return conductorVehiculolist;

        } catch (SQLException e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createVehiculoConductor(String conductor_id, String vehiculo_id) {
        int inserted = 0;
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("INSERT INTO vehiculo_conductor (conductor_id, vehiculo_id) VALUES (?,?)");

            ps.setString(1, conductor_id);
            ps.setString(2, vehiculo_id);

            System.out.println(ps);

            inserted = ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (inserted > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteVehiculoConductor(String conductor_id, String vehiculo_id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("DELETE FROM vehiculo_conductor WHERE conductor_id=? and vehiculo_id=?");
            ps.setString(1, conductor_id);
            ps.setString(2, vehiculo_id);
            int deleted = ps.executeUpdate();

            conn.close();

            if (deleted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);

        }

        return true;
    }

}
