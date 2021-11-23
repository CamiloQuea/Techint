/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes.api;

import controller.TipoDocumentoIdentidadServiceImpl;
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
import model.TipoDocumentoIdentidad;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.http;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "ApiTipoDocumentoIdentidad", urlPatterns = {"/api/tipodocumentoidentidad"})
public class ApiTipoDocumentoIdentidad extends HttpServlet {

    TipoDocumentoIdentidadServiceImpl service = new TipoDocumentoIdentidadServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ArrayList<TipoDocumentoIdentidad> tdocumentoIdlist = service.getTiposDeDocumentoDeIdentidad();

        ArrayList<JSONObject> resTDocumentoId = new ArrayList();

        for (int i = 0; i < tdocumentoIdlist.size(); i++) {
            JSONObject obj = new JSONObject();
            TipoDocumentoIdentidad tdocumentoId = tdocumentoIdlist.get(i);

            obj.put("id", tdocumentoId.getId());
            obj.put("nombre", tdocumentoId.getName());

            resTDocumentoId.add(obj);

        }

        out.println(resTDocumentoId);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            JSONObject payload;

            payload = http.getBody(request);

            String nombre = (String) payload.get("nombre");

            boolean rs = service.createTipoDeDocumentoDeIdentidad(nombre);

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

        boolean res = service.deleteTipoDeDocumentoDeIdentidad(id);

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

            boolean res = service.updateTipoDeDocumentoDeIdentidadById(id, nombre);

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
