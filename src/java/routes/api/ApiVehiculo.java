/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes.api;

import ServicesInterfaces.VehiculoService;
import controller.VehiculoServiceImpl;
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
import model.Vehiculo;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiVehiculo", urlPatterns = {"/api/vehiculo"})
public class ApiVehiculo extends HttpServlet {

    VehiculoService service = new VehiculoServiceImpl();
//    

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
        ArrayList<Vehiculo> listVehiculo = service.getVehiculos();

        ArrayList<JSONObject> resTipoVehiculo = new ArrayList();

        for (int i = 0; i < listVehiculo.size(); i++) {
            JSONObject obj = new JSONObject();
            Vehiculo vehiculo = listVehiculo.get(i);

            obj.put("id", vehiculo.getId());
            obj.put("codigo", vehiculo.getTipoVehiculo_id());
            obj.put("detalle", vehiculo.getPlaca());

            resTipoVehiculo.add(obj);

        }

        out.println(resTipoVehiculo);

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

            String tipo_vehiculo = (String) payload.get("tipo_vehiculo");
            String placa = (String) payload.get("placa");

            boolean rs = service.createVehiculo(tipo_vehiculo, placa);

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }

        } catch (Error ex) {

            JSONObject res = new JSONObject();
            res.put("error", true);
            res.put("mensaje", ex.getMessage());
            out.println(res);
        } catch (ParseException ex) {
            Logger.getLogger(ApiVehiculo.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean res = service.deleteVehiculo(id);

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
            String tipo_vehiculo = (String) payload.get("tipo_vehiculo");
            String placa = (String) payload.get("placa");

            boolean res = service.updateVehiculo(id, tipo_vehiculo, placa);

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
