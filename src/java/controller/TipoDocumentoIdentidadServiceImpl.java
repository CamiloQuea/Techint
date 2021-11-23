/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ServicesInterfaces.TipoDocumentoIdentidadService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoDocumentoIdentidad;
import util.connection;

/**
 *
 * @author Camilo
 */
public class TipoDocumentoIdentidadServiceImpl implements TipoDocumentoIdentidadService {

    @Override
    public ArrayList<TipoDocumentoIdentidad> getTiposDeDocumentoDeIdentidad() {
        ArrayList<TipoDocumentoIdentidad> tdocumentoIdlist = new ArrayList();
        Connection conn = connection.getConnection();
        try {

            PreparedStatement statement = conn.
                    prepareStatement("select * from tipo_documento_identidad");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                TipoDocumentoIdentidad user = new TipoDocumentoIdentidad(rs.getString(1), rs.getString(2));

                tdocumentoIdlist.add(user);

            }

            conn.close();

        } catch (SQLException e) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(e);
            }
            System.out.println(e);

        }
        return tdocumentoIdlist;
    }

    @Override
    public boolean createTipoDeDocumentoDeIdentidad(String nombre) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into tipo_documento_identidad (tipoDocumentoIdentidad_id,tipoDocumentoIdentidad_nombre) values ( (select newTipoDocumentoIdentidad_id()),?)");

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
    public boolean deleteTipoDeDocumentoDeIdentidad(String id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from tipo_documento_identidad where tipoDocumentoIdentidad_id = ?");
            ps.setString(1, id);
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
    public boolean updateTipoDeDocumentoDeIdentidadById(String id, String nombre) {
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE tipo_documento_identidad SET tipoDocumentoIdentidad_nombre=? "
                    + "where tipoDocumentoIdentidad_id=?";

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

        } catch (SQLException e) {
            throw new Error(e);
        }
    }

}
