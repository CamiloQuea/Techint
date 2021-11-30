/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.ConductorProyectoService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.ConductorProyecto;
import util.connection;
import java.sql.Connection;
import java.sql.SQLException;

import model.ConductorVehiculo;

/**
 *
 * @author Camilo
 */
public class ConductorProyectoServiceImpl implements ConductorProyectoService {

    @Override
    public ArrayList<ConductorProyecto> getProyectosConductores() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("SELECT c.conductor_id,c.conductor_nombre,"
                            + "c.conductor_apellido,p.proyecto_id,p.proyecto_cod,p.proyecto_nombre FROM "
                            + "conductor_proyecto vc "
                            + "INNER JOIN proyecto p on p.proyecto_id=vc.proyecto_id "
                            + "INNER JOIN conductor c on vc.conductor_id=c.conductor_id ");

            ResultSet rs = statement.executeQuery();

            ArrayList<ConductorProyecto> conductorProyectolist = new ArrayList();

            while (rs.next()) {

                ConductorProyecto proyectoconductor = new ConductorProyecto(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );

                conductorProyectolist.add(proyectoconductor);

            }

            conn.close();

            return conductorProyectolist;

        } catch (SQLException e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createProyectoConductor(String conductor_id, String proyecto_id) {
        int inserted = 0;
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("INSERT INTO conductor_proyecto (conductor_id, proyecto_id) VALUES (?,?)");

            ps.setString(1, conductor_id);
            ps.setString(2, proyecto_id);

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
    public boolean deleteProyectoConductor(String conductor_id, String proyecto_id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("DELETE FROM conductor_proyecto WHERE conductor_id=? and proyecto_id=?");
            ps.setString(1, conductor_id);
            ps.setString(2, proyecto_id);
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
