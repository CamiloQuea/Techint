/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesInterfaces;
import java.util.ArrayList;
import model.User;
/**
 *
 * @author Camilo
 */
public interface UserService {
    
       abstract public ArrayList<User> getUsers();
       
       abstract public User verifyUser(String email);
       
       abstract public boolean createUser(String nombre,String apellido,String correo,String password,String rol_id);
       
       abstract public boolean updateUser(String user_id, String nombre, String apellido, String correo, String password, String rol_id);
       
       abstract public boolean deleteUser(String user_id);     
    
}
