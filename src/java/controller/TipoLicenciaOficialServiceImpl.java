/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ServicesInterfaces.TipoLicenciaOficialService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoDocumentoIdentidad;
import model.TipoLicenciaInterna;
import model.TipoLicenciaOficial;
import util.connection;

/**
 *
 * @author Camilo
 */
public class TipoLicenciaOficialServiceImpl implements TipoLicenciaOficialService {

    @Override
    public ArrayList<TipoLicenciaOficial> getTiposLicenciaOficial() {
        ArrayList<TipoLicenciaOficial> tLicenciaOficiallist = new ArrayList();
        Connection conn = connection.getConnection();
        try {

            PreparedStatement statement = conn.
                    prepareStatement("SELECT tipoLicenciaOficial_id,tipoLicenciaOficial_nombre,p.pais_id,pais_nombre FROM tipo_licencia_oficial tlo inner JOIN pais p on p.pais_id=tlo.pais_id");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                TipoLicenciaOficial tipoLicenciaOficial = new TipoLicenciaOficial(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));

                tLicenciaOficiallist.add(tipoLicenciaOficial);

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
        return tLicenciaOficiallist;
    }

    @Override
    public boolean createTipoLicenciaOficial(String nombre, String pais_id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into tipo_licencia_oficial (tipoLicenciaOficial_id,tipoLicenciaOficial_nombre,pais_id) values ( (select newTipoLicenciaOficial_id()),?,?)");

            ps.setString(1, nombre);
            ps.setString(2, pais_id);

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
    public boolean deleteTipoLicenciaOficial(String id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from tipo_licencia_oficial where tipoLicenciaOficial_id = ?");
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
    public boolean updateTipoLicenciaOficialById(String id, String nombre, String pais_id) {
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE tipo_licencia_oficial SET tipoLicenciaOficial_nombre=? , pais_id=? "
                    + "where tipoLicenciaOficial_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);
            
            

            ps.setString(1, nombre);
            ps.setString(2, pais_id);
            ps.setString(3, id);
            System.out.println(ps);

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
