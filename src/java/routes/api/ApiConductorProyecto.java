package routes.api;

import controller.ConductorProyectoServiceImpl;
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
import model.ConductorProyecto;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiConductorProyecto", urlPatterns = {"/api/conductor/proyecto"})
public class ApiConductorProyecto extends HttpServlet {

    ConductorProyectoServiceImpl service = new ConductorProyectoServiceImpl();

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
        ArrayList<ConductorProyecto> conductorlist = service.getProyectosConductores();

        ArrayList<JSONObject> resConductorVehiculo = new ArrayList();

        for (int i = 0; i < conductorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            ConductorProyecto proyecto = conductorlist.get(i);

            obj.put("conductor_id", proyecto.getConductor_id());
            obj.put("conductor_nombre", proyecto.getConductor_nombre());
            obj.put("conductor_apellido", proyecto.getConductor_apellido());
            obj.put("proyecto_id", proyecto.getProyecto_id());
            obj.put("proyecto_codigo", proyecto.getProyecto_codigo());
            obj.put("proyecto_nombre", proyecto.getProyecto_nombre());

            resConductorVehiculo.add(obj);

        }

        out.println(resConductorVehiculo);

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

            String conductor = (String) payload.get("conductor");
            String proyecto = (String) payload.get("proyecto");

            boolean rs = service.createProyectoConductor(conductor, proyecto);

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiConductorProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//////

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String conductor = request.getParameter("conductor");
        String proyecto = request.getParameter("proyecto");

        System.out.println("TEST: " + conductor + proyecto);

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
        boolean res = service.deleteProyectoConductor(conductor, proyecto);

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
//    @Override
//    protected void doPut(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//
//        try {
//            JSONObject payload = http.getBody(request);
//            
//            String id = (String) payload.get("id");
//            String nombre = (String) payload.get("nombre");
//            String apellido = (String) payload.get("apellido");
//            String documento = (String) payload.get("documento");
//            Boolean activo = (Boolean) payload.get("activo");
//            String empresa_id = (String) payload.get("empresa_id");
//            String grupoSanguineo_id = (String) payload.get("grupoSanguineo_id");
//            String tipoDocumentoIdentidad_id = (String) payload.get("tipoDocumentoIdentidad_id");
//            String pais_id = (String) payload.get("pais_id");
//
//            
//            
//           
//            boolean res = service.updateConductor(
//            id,
//            empresa_id,
//            grupoSanguineo_id,
//            tipoDocumentoIdentidad_id,
//            pais_id,
//            documento,
//            nombre,
//            apellido,
//            activo
//            );
//
//            JSONObject msj = new JSONObject();
//
//            if (res) {
//                msj.put("error", false);
//                out.println(msj);
//            } else {
//                msj.put("error", true);
//                out.println(msj);
//            }
//
//        } catch (ParseException ex) {
//            Logger.getLogger(ApiConductorVehiculo.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
