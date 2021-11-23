/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.ConductorService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;
import util.connection;
import java.sql.Connection;
import java.sql.SQLException;
import model.Conductor;

/**
 *
 * @author Camilo
 */
public class ConductorServiceImpl implements ConductorService {

    @Override
    public ArrayList<Conductor> getConductores() {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("SELECT conductor_id,conductor_documento,conductor_nombre,conductor_apellido,activo,"
                            + "e.empresa_id,e.empresa_nombre ,gs.grupoSanguineo_id,"
                            + "gs.grupoSanguineo_nombre,tdi.tipoDocumentoIdentidad_id,"
                            + "tdi.tipoDocumentoIdentidad_nombre,p.pais_id,p.pais_nombre FROM conductor c "
                            + "INNER JOIN empresa e on e.empresa_id=c.empresa_id "
                            + "INNER JOIN grupo_sanguineo gs on gs.grupoSanguineo_id=c.grupoSanguineo_id "
                            + "INNER JOIN tipo_documento_identidad tdi on tdi.tipoDocumentoIdentidad_id=c.tipoDocumentoIdentidad_id "
                            + "INNER JOIN pais p on p.pais_id=c.pais_id");

            ResultSet rs = statement.executeQuery();

            ArrayList<Conductor> conductorlist = new ArrayList();

            while (rs.next()) {

                Conductor conductor = new Conductor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)
                );

                conductorlist.add(conductor);

            }

            conn.close();

            return conductorlist;

        } catch (SQLException e) {

            throw new Error(e);

        }

    }

    @Override
    public boolean deleteConductor(String id) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from conductor where conductor_id = ?");
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
    public boolean createConductor(
            String empresa_id,
            String gruposanguineo_id,
            String tipoDocumentoIdentidad_id,
            String pais_id,
            String conductor_documento,
            String conductor_nombre,
            String conductor_apellido,
            Boolean activo
    ) {
        int inserted = 0;
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into conductor ("
                            + "conductor_id, empresa_id, grupoSanguineo_id,"
                            + " tipoDocumentoIdentidad_id, pais_id,"
                            + " conductor_documento, conductor_nombre, conductor_apellido, activo"
                            + ") "
                            + "values ( (select newConductor_id()),?, ?, ?, ? , ?,?,?,?)");

            ps.setString(1, empresa_id);
            ps.setString(2, gruposanguineo_id);
            ps.setString(3, tipoDocumentoIdentidad_id);
            ps.setString(4, pais_id);
            ps.setString(5, conductor_documento);
            ps.setString(6, conductor_nombre);
            ps.setString(7, conductor_apellido);

            if (activo) {
                ps.setInt(8, 1);
            } else {
                ps.setInt(8, 0);
            }

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
    public boolean updateConductor(
            String id,
            String empresa_id,
            String gruposanguineo_id,
            String tipoDocumentoIdentidad_id,
            String pais_id,
            String conductor_documento,
            String conductor_nombre,
            String conductor_apellido,
            Boolean activo
    ) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE conductor SET "
                    + "empresa_id=?, grupoSanguineo_id=?, tipoDocumentoIdentidad_id=?,"
                    + " pais_id=?, conductor_documento=?, conductor_nombre=?,"
                    + " conductor_apellido=?,activo=? "
                    + "WHERE conductor_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, empresa_id);
            ps.setString(2, gruposanguineo_id);
            ps.setString(3, tipoDocumentoIdentidad_id);
            ps.setString(4, pais_id);
            ps.setString(5, conductor_documento);
            ps.setString(6, conductor_nombre);
            ps.setString(7, conductor_apellido);
            ps.setBoolean(8, activo);
            ps.setString(9, id);

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
