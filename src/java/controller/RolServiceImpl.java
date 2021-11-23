/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.RolService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Rol;
import util.connection;

/**
 *
 * @author Camilo
 */
public class RolServiceImpl implements RolService {

    @Override
    public ArrayList<Rol> getRols() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from rol");

            ResultSet rs = statement.executeQuery();

            ArrayList<Rol> rollist = new ArrayList<Rol>();

            while (rs.next()) {

                Rol user = new Rol(rs.getString(1), rs.getString(2));

                rollist.add(user);

            }

            conn.close();

            return rollist;
        } catch (Exception e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createRol(String nombre) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into rol (rol_id,rol_nombre) values ( (select newRol_id()),?)");

            ps.setString(1, nombre);

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
    public boolean deleteRol(String rol_id) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from rol where rol_id = ?");
            ps.setString(1, rol_id);
            int deleted = ps.executeUpdate();

            conn.close();

            if (deleted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

        }

        return true;

    }

    @Override
    public boolean updateRolById(String rol_id, String nombre) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE rol SET rol_nombre=? "
                    + "where rol_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, rol_id);

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

}
