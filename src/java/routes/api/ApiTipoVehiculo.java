/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes.api;

import controller.TipoVehiculoServiceImpl;
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
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiTipoVehiculo", urlPatterns = {"/api/tipovehiculo"})
public class ApiTipoVehiculo extends HttpServlet {

    TipoVehiculoServiceImpl service = new TipoVehiculoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ArrayList<TipoVehiculo> listTipoVehiculo = service.getTipoVehiculos();
        
        System.out.println(listTipoVehiculo);

        ArrayList<JSONObject> resTipoVehiculo = new ArrayList();

        for (int i = 0; i < listTipoVehiculo.size(); i++) {
            JSONObject obj = new JSONObject();
            TipoVehiculo user = listTipoVehiculo.get(i);

            obj.put("id", user.getId());
            obj.put("codigo", user.getCod());
            obj.put("detalle", user.getDetalle());

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
        try {
            JSONObject res = new JSONObject();

            JSONObject payload = http.getBody(request);

            if (payload == null) {
                throw new Error("No hay datos");
            }

            String codigo = (String) payload.get("codigo");
            String detalle = (String) payload.get("detalle");

            boolean rs = service.createTipoVehiculo(codigo, detalle);

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
            Logger.getLogger(ApiTipoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean res = service.deleteTipoVehiculo(id);

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

        try {
            JSONObject payload = http.getBody(request);

            if (payload == null) {
                throw new Error("No hay datos");
            }

            String id = (String) payload.get("id");
            String codigo = (String) payload.get("codigo");
            String detalle = (String) payload.get("detalle");

            boolean res = service.updateTipoVehiculo(id, codigo, detalle);

            

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
