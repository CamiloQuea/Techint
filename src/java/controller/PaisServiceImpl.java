/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ServicesInterfaces.PaisService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Pais;
import util.connection;

/**
 *
 * @author Camilo
 */
public class PaisServiceImpl implements PaisService {

    @Override
    public ArrayList<Pais> getPaises() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select *  from pais");

            ResultSet rs = statement.executeQuery();

            ArrayList<Pais> paislist = new ArrayList();

            while (rs.next()) {

                Pais pais = new Pais(rs.getString(1), rs.getString(2), rs.getString(3));

                paislist.add(pais);

            }

            conn.close();

            return paislist;
        } catch (Exception e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createPais(String nombre, String iso) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into pais (pais_id,pais_iso,pais_nombre) values ( (select newPais_id()),?,?)");

            ps.setString(1, iso);
            ps.setString(2, nombre);
            
            

            int inserted = ps.executeUpdate();

            
            
            conn.close();

            if (inserted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new Error(e);
        }
    }

    @Override
    public boolean updatePais(String pais_id, String nombre, String iso) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE pais SET pais_iso=?, pais_nombre=? "
                    + "where pais_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, iso);
            ps.setString(3, pais_id);

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
    public boolean deletePais(String pais_id) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from pais where pais_id = ?");

            ps.setString(1, pais_id);
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

}
