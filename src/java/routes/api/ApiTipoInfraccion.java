/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes.api;

import controller.TipoInfraccionServiceImpl;
import model.TipoInfraccion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TipoVehiculo;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiTipoInfraccion", urlPatterns = {"/api/tipoinfraccion"})
public class ApiTipoInfraccion extends HttpServlet {

    TipoInfraccionServiceImpl service = new TipoInfraccionServiceImpl();

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
        ArrayList<TipoInfraccion> listTipoInfraccion = service.getTipoInfraccion();

        ArrayList<JSONObject> resTipoInfraccion = new ArrayList();

        for (int i = 0; i < listTipoInfraccion.size(); i++) {
            JSONObject obj = new JSONObject();
            TipoInfraccion user = listTipoInfraccion.get(i);

            obj.put("id", user.getId());
            obj.put("nombre", user.getNombre());
            obj.put("detalle", user.getDetalle());

            resTipoInfraccion.add(obj);

        }

        out.println(resTipoInfraccion);

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
            JSONObject res = new JSONObject();

            JSONObject payload = http.getBody(request);

            if (payload == null) {
                throw new Error("No hay datos");
            }

            String nombre = (String) payload.get("nombre");
            String detalle = (String) payload.get("detalle");

            boolean rs = service.createTipoInfraccion(nombre, detalle);

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
//            }
        } catch (Error ex) {

            JSONObject res = new JSONObject();
            res.put("error", true);
            res.put("mensaje", ex.getMessage());
            out.println(res);
        } catch (ParseException ex) {
            Logger.getLogger(ApiTipoInfraccion.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean res = service.deleteTipoInfraccion(id);

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

            if (payload == null) {
                throw new Error("No hay datos");
            }

            String id = (String) payload.get("id");
            String nombre = (String) payload.get("nombre");
            String detalle = (String) payload.get("detalle");

            boolean res = service.updateTipoInfraccion(id, nombre, detalle);

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
        } catch (Error ex) {

            JSONObject res = new JSONObject();
            res.put("error", true);
            res.put("mensaje", ex.getMessage());
            out.println(res);
        }

    }
}
