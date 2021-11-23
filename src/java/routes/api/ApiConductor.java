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

        PrintWriter out = response.getWriter();

        response.setContentType("application/json;charset=UTF-8");

        ArrayList<Conductor> conductorlist = service.getConductores();

        ArrayList<JSONObject> resConductor = new ArrayList();

        for (int i = 0; i < conductorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            Conductor user = conductorlist.get(i);

            obj.put("id", user.getId());
            obj.put("documento", user.getDocumento());
            obj.put("nombre", user.getNombre());
            obj.put("apellido", user.getNombre());
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

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        
//        
//        PrintWriter out = response.getWriter();
//        try {
//            JSONObject payload = http.getBody(request);
//
//            
//            
//            String nombre = (String) payload.get("nombre");
//            String apellido = (String) payload.get("apellido");
//            String correo = (String) payload.get("correo");
//            String contraseña = (String) payload.get("contra");
//            String rol_id = (String) payload.get("rol_id");
//
//            contraseña = BCrypt.hashpw(contraseña, BCrypt.gensalt(12));
//
//            boolean rs = service.createUser(nombre, apellido, correo, contraseña, rol_id);
//
//            JSONObject res = new JSONObject();
//
//            if (rs) {
//
//                res.put("error", false);
//                out.println(res);
//
//            } else {
//                res.put("error", true);
//                out.println(res);
//            }
//        } catch (ParseException ex) {
//            Logger.getLogger(ApiConductor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        PrintWriter out = response.getWriter();
//
//        String id = request.getParameter("id");
//
//        
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        boolean res = service.deleteUser(id);
//
//        JSONObject msj = new JSONObject();
//
//        if (res) {
//            msj.put("error", false);
//            out.println(msj);
//        } else {
//            msj.put("error", true);
//            out.println(msj);
//        }
//
//    }
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
//            String user_id = (String) payload.get("id");
//            String nombre = (String) payload.get("nombre");
//            String apellido = (String) payload.get("apellido");
//            String correo = (String) payload.get("correo");
//            String contraseña = (String) payload.get("contra");
//            String rol_id = (String) payload.get("rol_id");
//
//            
//            
//            if (!contraseña.isEmpty()) {
//                contraseña = BCrypt.hashpw(contraseña, BCrypt.gensalt(12));
//            }
//
//            boolean res = service.updateUser(user_id, nombre, apellido, correo, contraseña, rol_id);
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
//            Logger.getLogger(ApiConductor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
