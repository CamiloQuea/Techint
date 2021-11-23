/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.EmpresaService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Empresa;
import util.connection;

/**
 *
 * @author Camilo
 */
public class EmpresaServiceImpl implements EmpresaService {

    @Override
    public ArrayList<Empresa> getEmpresas() {
        ArrayList<Empresa> empresalist = new ArrayList();
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select * from empresa");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Empresa empresa = new Empresa(rs.getString(1), rs.getString(2), rs.getString(3));

                empresalist.add(empresa);

            }

            conn.close();

        } catch (SQLException e) {

            System.out.println(e);

        }
        return empresalist;
    }

    @Override
    public boolean createEmpresa(String ruc, String nombre) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into empresa (empresa_id,empresa_ruc,empresa_nombre) values ( (select newEmpresa_id()),?,?)");

            ps.setString(1, ruc);
            ps.setString(2, nombre);

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
    public boolean deleteEmpresa(String id) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from empresa where empresa_id = ?");
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
    public boolean updateEmpresaById(String rol_id, String ruc, String nombre) {
        int updated = 0;
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE empresa SET empresa_ruc=?,empresa_nombre=? "
                    + "where empresa_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, ruc);
            ps.setString(2, nombre);
            ps.setString(3, rol_id);

            updated = ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

}
