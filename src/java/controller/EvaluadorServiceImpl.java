/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import ServicesInterfaces.EvaluadorService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Evaluador;
import util.connection;

/**
 *
 * @author Camilo
 */
public class EvaluadorServiceImpl implements EvaluadorService {

    @Override
    public ArrayList<Evaluador> getEvaluadores() {
        ArrayList<Evaluador> evaluadorlist = new ArrayList();
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("SELECT evaluador_id,evaluador_nombre,evaluador_apellido,e.empresa_id ,empresa_nombre FROM evaluador ev INNER JOIN empresa e on e.empresa_id=ev.empresa_id");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Evaluador evaluador = new Evaluador(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

                evaluadorlist.add(evaluador);

            }

            conn.close();

            return evaluadorlist;

        } catch (SQLException e) {

            System.out.println(e);

        }
        return evaluadorlist;
    }

    @Override
    public boolean createEvaluador(String empresa_id, String nombre, String apellido) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("INSERT INTO evaluador (evaluador_id, empresa_id, evaluador_nombre, evaluador_apellido) VALUES ((select newEvaluador_id()),?,?,?)");

            ps.setString(1, empresa_id);
            ps.setString(2, nombre);
            ps.setString(3, apellido);

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
    public boolean deleteEvaluador(String id) {
        int deleted = 0;

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("DELETE FROM evaluador WHERE evaluador_id=?");
            ps.setString(1, id);
            deleted = ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {

        }

        if (deleted > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateEvaluador(String id, String empresa_id, String nombre, String apellido) {
        int updated = 0;
        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE evaluador SET empresa_id=?,evaluador_nombre=?,evaluador_apellido=? "
                    + " where evaluador_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, empresa_id);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, id);
            
            System.out.println(ps);

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
