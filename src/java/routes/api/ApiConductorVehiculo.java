package routes.api;

import controller.ConductorVehiculoServiceImpl;
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
import model.ConductorVehiculo;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiConductorVehiculo", urlPatterns = {"/api/conductor/vehiculo"})
public class ApiConductorVehiculo extends HttpServlet {

    ConductorVehiculoServiceImpl service = new ConductorVehiculoServiceImpl();

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
        ArrayList<ConductorVehiculo> conductorlist = service.getVehiculosConductores();

        ArrayList<JSONObject> resConductorVehiculo = new ArrayList();

        for (int i = 0; i < conductorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            ConductorVehiculo vehiculo = conductorlist.get(i);

            obj.put("conductor_id", vehiculo.getConductor_id());
            obj.put("conductor_nombre", vehiculo.getConductor_nombre());
            obj.put("conductor_apellido", vehiculo.getConductor_apellido());
            obj.put("vehiculo_id", vehiculo.getVehiculo_id());
            obj.put("vehiculo_placa", vehiculo.getVehiculo_placa());
            obj.put("tipoVehiculo_id", vehiculo.getTipoVehiculo_id());
            obj.put("tipoVehiculo_nombre", vehiculo.getTipoVehiculo_nombre());

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
            String vehiculo = (String) payload.get("vehiculo");

            boolean rs = service.createVehiculoConductor(conductor, vehiculo);

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiConductorVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
////

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String vehiculo = request.getParameter("vehiculo");
        String conductor = request.getParameter("conductor");

        System.out.println(vehiculo + conductor);

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
        boolean res = service.deleteVehiculoConductor(conductor, vehiculo);

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
