/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ServicesInterfaces.GrupoSanguineoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.GrupoSanguineo;
import util.connection;

/**
 *
 * @author Camilo
 */
public class GrupoSanguineoServiceImpl implements GrupoSanguineoService {

    @Override
    public ArrayList<GrupoSanguineo> getGrupoSanguineo() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from grupo_sanguineo");

            ResultSet rs = statement.executeQuery();

            ArrayList<GrupoSanguineo> gsanguineolist = new ArrayList();

            while (rs.next()) {

                GrupoSanguineo gsanguineo = new GrupoSanguineo(rs.getString(1), rs.getString(2));

                gsanguineolist.add(gsanguineo);

            }

            conn.close();

            return gsanguineolist;
        } catch (Exception e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createGrupoSanguineo(String nombre) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into grupo_sanguineo (grupoSanguineo_id,grupoSanguineo_nombre) values ( (select newGrupoSanguineo_id()),?)");

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
    public boolean deleteGrupoSanguineo(String id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from grupo_sanguineo where grupoSanguineo_id = ?");
            ps.setString(1, id);
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

    @Override
    public boolean updateGrupoSanguineoById(String id, String nombre) {
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE grupo_sanguineo SET grupoSanguineo_nombre=? "
                    + "where grupoSanguineo_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, id);

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
