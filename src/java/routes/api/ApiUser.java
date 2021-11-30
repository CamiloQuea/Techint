package routes.api;

import controller.UserServiceImpl;
import ServicesInterfaces.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiUser", urlPatterns = {"/api/user"})
public class ApiUser extends HttpServlet {

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("application/json;charset=UTF-8");
        User userLogin = (User) request.getSession().getAttribute("usuario");
        if (userLogin == null) {

            JSONObject res = new JSONObject();

            res.put("error", true);
            response.setStatus(401);
            out.println(res);
            return;
        }
        ArrayList<User> userlist = service.getUsers();

        ArrayList<JSONObject> resUser = new ArrayList();

        for (int i = 0; i < userlist.size(); i++) {
            JSONObject obj = new JSONObject();
            User user = userlist.get(i);

            obj.put("id", user.getId());
            obj.put("nombre", user.getNombre());
            obj.put("apellido", user.getApellido());
            obj.put("correo", user.getEmail());
            obj.put("rol_id", user.getRol_id());
            obj.put("rol", user.getRol_name());

            resUser.add(obj);

        }

        out.println(resUser);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        User userLogin = (User) request.getSession().getAttribute("usuario");
        if (userLogin == null) {

            JSONObject res = new JSONObject();

            res.put("error", true);
            response.setStatus(401);
            out.println(res);
            return;
        }
        try {
            JSONObject payload = http.getBody(request);

            String nombre = (String) payload.get("nombre");
            String apellido = (String) payload.get("apellido");
            String correo = (String) payload.get("correo");
            String contraseña = (String) payload.get("contra");
            String rol_id = (String) payload.get("rol_id");

            contraseña = BCrypt.hashpw(contraseña, BCrypt.gensalt(12));

            boolean rs = service.createUser(nombre, apellido, correo, contraseña, rol_id);

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        User userLogin = (User) request.getSession().getAttribute("usuario");
        if (userLogin == null) {

            JSONObject res = new JSONObject();

            res.put("error", true);
            response.setStatus(401);
            out.println(res);
            return;
        }
        boolean res = service.deleteUser(id);

        JSONObject msj = new JSONObject();

        if (res) {
            msj.put("error", false);
            out.println(msj);
        } else {
            msj.put("error", true);
            out.println(msj);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        User userLogin = (User) request.getSession().getAttribute("usuario");
        if (userLogin == null) {

            JSONObject res = new JSONObject();

            res.put("error", true);
            response.setStatus(401);
            out.println(res);
            return;
        }
        try {
            JSONObject payload = http.getBody(request);

            String user_id = (String) payload.get("id");
            String nombre = (String) payload.get("nombre");
            String apellido = (String) payload.get("apellido");
            String correo = (String) payload.get("correo");
            String contraseña = (String) payload.get("contra");
            String rol_id = (String) payload.get("rol_id");

            if (!contraseña.isEmpty()) {
                contraseña = BCrypt.hashpw(contraseña, BCrypt.gensalt(12));
            }

            boolean res = service.updateUser(user_id, nombre, apellido, correo, contraseña, rol_id);

            JSONObject msj = new JSONObject();

            if (res) {
                msj.put("error", false);
                out.println(msj);
            } else {
                msj.put("error", true);
                out.println(msj);
            }

        } catch (ParseException ex) {
            Logger.getLogger(ApiUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
