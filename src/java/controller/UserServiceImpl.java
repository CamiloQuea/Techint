/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import ServicesInterfaces.UserService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.User;
import util.connection;
import java.sql.Connection;

/**
 *
 * @author Camilo
 */
public class UserServiceImpl implements UserService {

    @Override
    public ArrayList<User> getUsers() {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement statement = conn.
                    prepareStatement("select usuario_id,usuario_nombre,usuario_apellido,usuario_email,u.rol_id,rol_nombre from usuario u inner join rol r ON u.rol_id=r.rol_id");

            ResultSet rs = statement.executeQuery();

            ArrayList<User> userlist = new ArrayList();
            
            

            while (rs.next()) {

                
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

                userlist.add(user);

            }

            conn.close();

            return userlist;
            
        } catch (Exception e) {

            throw new Error(e);

        }

    }

    @Override
    public boolean deleteUser(String user_id) {

        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("delete from usuario where usuario_id = ?");
            ps.setString(1, user_id);
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
    public boolean createUser(String nombre, String apellido, String correo, String password, String rol_id) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("insert into usuario (usuario_id,usuario_nombre,usuario_apellido,usuario_email,usuario_password,rol_id) values ( (select newUser_id()),?, ?, ?, ? , ?)");

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, password);
            ps.setString(5, rol_id);

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
    public boolean updateUser(String user_id, String nombre, String apellido, String correo, String password, String rol_id) {

        try {

            Connection conn = connection.getConnection();

            String sql = "UPDATE usuario SET usuario_nombre=?,usuario_apellido=?,usuario_email=?,"
                    + (password.isEmpty() ? "" : "usuario_password=?,")
                    + "rol_id=? where usuario_id=?";

            PreparedStatement ps = conn.
                    prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);

            if (password.isEmpty()) {

                ps.setString(4, rol_id);
                ps.setString(5, user_id);

            } else {

                ps.setString(4, password);
                ps.setString(5, rol_id);
                ps.setString(6, user_id);

            }

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
    public User verifyUser(String email) {
        try {

            Connection conn = connection.getConnection();

            PreparedStatement ps = conn.
                    prepareStatement("select usuario_id,usuario_nombre,usuario_apellido,usuario_email,u.rol_id,rol_nombre,usuario_password from usuario u inner join rol r ON u.rol_id=r.rol_id WHERE u.usuario_email=? ");

            
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            
            if (rs.next()) {

                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));
                conn.close();
                return user;

            } else {
                User user = null;
                conn.close();
                return user;

            }

        } catch (Exception e) {

            System.out.println(e);
            throw new Error(e);

        }
    }

}
