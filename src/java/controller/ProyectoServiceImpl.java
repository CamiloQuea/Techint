/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.ProyectoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Proyecto;
import model.TipoVehiculo;
import util.connection;

/**
 *
 * @author Camilo
 */
public class ProyectoServiceImpl implements ProyectoService {

    @Override
    public ArrayList<Proyecto> getProyectos() {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from proyecto");

            ResultSet rs = statement.executeQuery();

            ArrayList<Proyecto> proyectoList = new ArrayList<Proyecto>();

            while (rs.next()) {

                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4));

                Proyecto proyecto = new Proyecto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));

                proyectoList.add(proyecto);

            }

            conn.close();

            return proyectoList;
        } catch (Exception e) {

            throw new Error(e);

        }

    }

    @Override
    public boolean createProyecto(String codigo, String usuario_id, String description) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into proyecto (proyecto_id,proyecto_cod,usuario_id,proyecto_nombre) values ( (select newProyecto_id()),?,?, ?)");

            ps.setString(1, codigo);
            ps.setString(2, usuario_id);
            ps.setString(3, description);

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
    public boolean updateProyecto(String id, String codigo, String usuario_id, String nombre) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE proyecto SET proyecto_cod=?,proyecto_nombre=? where proyecto_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, codigo);
            ps.setString(2, nombre);
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
    public boolean deleteProyecto(String proyecto_id) throws Error {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from proyecto where proyecto_id = ?");
            ps.setString(1, proyecto_id);
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
