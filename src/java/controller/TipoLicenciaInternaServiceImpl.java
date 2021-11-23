/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import ServicesInterfaces.TipoLicenciaInternaService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoDocumentoIdentidad;
import model.TipoLicenciaInterna;
import util.connection;

/**
 *
 * @author Camilo
 */
public class TipoLicenciaInternaServiceImpl implements TipoLicenciaInternaService {

    @Override
    public ArrayList<TipoLicenciaInterna> getTiposLicenciaInterna() {
        ArrayList<TipoLicenciaInterna> tLicenciaInternalist = new ArrayList();
        Connection conn = connection.getConnection();
        try {

            PreparedStatement statement = conn.
                    prepareStatement("select * from tipo_licencia_interna");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                TipoLicenciaInterna tipoLicenciaInterna = new TipoLicenciaInterna(rs.getString(1), rs.getString(2));

                tLicenciaInternalist.add(tipoLicenciaInterna);

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
        return tLicenciaInternalist;
    }

    @Override
    public boolean createTipoLicenciaInterna(String nombre) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into tipo_licencia_interna (tipoLicenciaInterna_id,tipoLicenciaInterna_nombre) values ( (select newTipoLicenciaInterna_id()),?)");

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
    public boolean deleteTipoLicenciaInterna(String id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from tipo_licencia_interna where tipoLicenciaInterna_id = ?");
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
    public boolean updateTipoLicenciaInternaById(String id, String nombre) {
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE tipo_licencia_interna SET tipoLicenciaInterna_nombre=? "
                    + "where tipoLicenciaInterna_id=?";

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
