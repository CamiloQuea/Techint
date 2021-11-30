/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ServicesInterfaces.ConductorEvaluacionService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;
import util.connection;
import java.sql.Connection;
import java.sql.SQLException;

import model.ConductorEvaluacion;

/**
 *
 * @author Camilo
 */
public class ConductorEvaluacionServiceImpl implements ConductorEvaluacionService {

    @Override
    public ArrayList<ConductorEvaluacion> getEvaluacionConductores() {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("SELECT e.evaluacion_id,c.conductor_id,c.conductor_nombre,"
                            + "c.conductor_apellido,ev.evaluador_id,ev.evaluador_nombre,"
                            + "ev.evaluador_apellido,e.evaluacion_examTeorico,e.evaluacion_examPractico "
                            + "FROM evaluacion e "
                            + "INNER JOIN evaluador ev on e.evaluador_id=ev.evaluador_id "
                            + "INNER JOIN conductor c on c.conductor_id=e.conductor_id");

            ResultSet rs = statement.executeQuery();

            ArrayList<ConductorEvaluacion> conductorEvaluacioneslist = new ArrayList();

            while (rs.next()) {

                ConductorEvaluacion evaluacionconductor = new ConductorEvaluacion(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getDouble(9)
                );

                conductorEvaluacioneslist.add(evaluacionconductor);

            }

            conn.close();

            return conductorEvaluacioneslist;

        } catch (SQLException e) {

            throw new Error(e);

        }
    }

    @Override
    public boolean createEvaluacionConductor(String conductor_id, String evaluador_id, Double notaExamTeo, Double notaExamPrac) {
        int inserted = 0;
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("INSERT INTO evaluacion (evaluacion_id,conductor_id, evaluador_id,evaluacion_examTeorico,evaluacion_examPractico) VALUES ((SELECT newEvaluacion_id()),?,?,?,?)");

            ps.setString(1, conductor_id);
            ps.setString(2, evaluador_id);
            ps.setDouble(3, notaExamTeo);
            ps.setDouble(4, notaExamPrac);

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
    public boolean deleteEvaluacionConductor(String evaluacion_id) {
        try {

            System.out.println(evaluacion_id);

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("DELETE FROM evaluacion WHERE evaluacion_id=?");
            ps.setString(1, evaluacion_id);

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
    public boolean updateEvaluacionConductor(String evaluacion_id, String conductor_id, String evaluador_id, Double notaExamTeo, Double notaExamPrac) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE evaluacion SET conductor_id=?, evaluador_id=?, evaluacion_examTeorico=?, evaluacion_examPractico=? WHERE evaluacion_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, conductor_id);
            ps.setString(2, evaluador_id);
            ps.setDouble(3, notaExamTeo);
            ps.setDouble(4, notaExamPrac);
            ps.setString(5, evaluacion_id);

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
