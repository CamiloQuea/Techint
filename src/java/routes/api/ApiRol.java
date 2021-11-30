/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes.api;

import ServicesInterfaces.RolService;
import controller.RolServiceImpl;
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
import model.Rol;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiRol", urlPatterns = {"/api/rol"})
public class ApiRol extends HttpServlet {

    RolService service = new RolServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

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
        ArrayList<Rol> rollist = service.getRols();

        ArrayList<JSONObject> resRol = new ArrayList();

        for (int i = 0; i < rollist.size(); i++) {
            JSONObject obj = new JSONObject();
            Rol rol = rollist.get(i);

            obj.put("id", rol.getRol_id());
            obj.put("nombre", rol.getRol_name());

            resRol.add(obj);

        }

        out.println(resRol);

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
            JSONObject payload;

            payload = http.getBody(request);

            String rol_nombre = (String) payload.get("rol_nombre");

            boolean rs = service.createRol(rol_nombre);

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
        boolean res = service.deleteRol(id);

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

            String rol_id = (String) payload.get("id");
            String rol_nombre = (String) payload.get("nombre");

            boolean res = service.updateRolById(rol_id, rol_nombre);

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
