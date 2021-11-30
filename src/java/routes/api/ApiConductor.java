package routes.api;

import controller.UserServiceImpl;
import ServicesInterfaces.UserService;
import controller.ConductorServiceImpl;
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
import model.Conductor;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiConductor", urlPatterns = {"/api/conductor"})
public class ApiConductor extends HttpServlet {

    ConductorServiceImpl service = new ConductorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        User userLogin = (User) request.getSession().getAttribute("usuario");
        if (userLogin == null) {

            JSONObject res = new JSONObject();

            res.put("error", true);
            response.setStatus(401);
            out.println(res);
            return;
        }

        ArrayList<Conductor> conductorlist = service.getConductores();

        ArrayList<JSONObject> resConductor = new ArrayList();

        for (int i = 0; i < conductorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            Conductor user = conductorlist.get(i);

            obj.put("id", user.getId());
            obj.put("documento", user.getDocumento());
            obj.put("nombre", user.getNombre());
            obj.put("apellido", user.getApellido());
            obj.put("activo", user.getActivo());
            obj.put("empresa_id", user.getEmpresa_id());
            obj.put("empresa_name", user.getEmpresa_nombre());
            obj.put("grupoSanguineo_id", user.getGrupoSanguineo_id());
            obj.put("grupoSanguineo_name", user.getGrupoSanguineo_nombre());
            obj.put("tipoDocumentoIdentidad_id", user.getTipoDocumentoIdentidad_id());
            obj.put("tipoDocumentoIdentidad_name", user.getTipoDocumentoIdentidad_nombre());
            obj.put("pais_id", user.getPais_id());
            obj.put("pais_name", user.getPais_nombre());

            resConductor.add(obj);

        }

        out.println(resConductor);

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
            String documento = (String) payload.get("documento");
            Boolean activo = (Boolean) payload.get("activo");
            String empresa_id = (String) payload.get("empresa_id");
            String grupoSanguineo_id = (String) payload.get("grupoSanguineo_id");
            String tipoDocumentoIdentidad_id = (String) payload.get("tipoDocumentoIdentidad_id");
            String pais_id = (String) payload.get("pais_id");

            System.out.println("aaaaaa:  " + activo);

            boolean rs = service.createConductor(
                    empresa_id,
                    grupoSanguineo_id,
                    tipoDocumentoIdentidad_id,
                    pais_id,
                    documento,
                    nombre,
                    apellido,
                    activo
            );

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiConductor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
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
        boolean res = service.deleteConductor(id);

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

            String id = (String) payload.get("id");
            String nombre = (String) payload.get("nombre");
            String apellido = (String) payload.get("apellido");
            String documento = (String) payload.get("documento");
            Boolean activo = (Boolean) payload.get("activo");
            String empresa_id = (String) payload.get("empresa_id");
            String grupoSanguineo_id = (String) payload.get("grupoSanguineo_id");
            String tipoDocumentoIdentidad_id = (String) payload.get("tipoDocumentoIdentidad_id");
            String pais_id = (String) payload.get("pais_id");

            boolean res = service.updateConductor(
                    id,
                    empresa_id,
                    grupoSanguineo_id,
                    tipoDocumentoIdentidad_id,
                    pais_id,
                    documento,
                    nombre,
                    apellido,
                    activo
            );

            JSONObject msj = new JSONObject();

            if (res) {
                msj.put("error", false);
                out.println(msj);
            } else {
                msj.put("error", true);
                out.println(msj);
            }

        } catch (ParseException ex) {
            Logger.getLogger(ApiConductor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
