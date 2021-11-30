/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes.api;

import ServicesInterfaces.RolService;
import controller.EmpresaServiceImpl;

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
import model.Empresa;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiEmpresa", urlPatterns = {"/api/empresa"})
public class ApiEmpresa extends HttpServlet {

    EmpresaServiceImpl service = new EmpresaServiceImpl();

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
        ArrayList<Empresa> empresalist = service.getEmpresas();

        ArrayList<JSONObject> resRol = new ArrayList();

        for (int i = 0; i < empresalist.size(); i++) {
            JSONObject obj = new JSONObject();
            Empresa empresa = empresalist.get(i);

            obj.put("id", empresa.getId());
            obj.put("ruc", empresa.getRuc());
            obj.put("nombre", empresa.getNombre());

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

            String nombre = (String) payload.get("nombre");
            String ruc = (String) payload.get("ruc");

            boolean rs = service.createEmpresa(ruc, nombre);

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
//    
//    

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
        boolean res = service.deleteEmpresa(id);

        JSONObject msj = new JSONObject();

        if (res) {
            msj.put("error", false);
            out.println(msj);
        } else {
            msj.put("error", true);
            out.println(msj);
        }

    }
//    

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            JSONObject payload = http.getBody(request);

            String id = (String) payload.get("id");
            String nombre = (String) payload.get("nombre");
            String ruc = (String) payload.get("ruc");
            User userLogin = (User) request.getSession().getAttribute("usuario");
            if (userLogin == null) {

                JSONObject res = new JSONObject();

                res.put("error", true);
                response.setStatus(401);
                out.println(res);
                return;
            }
            boolean res = service.updateEmpresaById(id, ruc, nombre);

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
