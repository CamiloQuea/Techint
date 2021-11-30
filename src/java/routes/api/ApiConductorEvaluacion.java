package routes.api;

import controller.ConductorEvaluacionServiceImpl;
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
import model.ConductorEvaluacion;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiConductorEvaluacion", urlPatterns = {"/api/conductor/evaluacion"})
public class ApiConductorEvaluacion extends HttpServlet {

    ConductorEvaluacionServiceImpl service = new ConductorEvaluacionServiceImpl();

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
        ArrayList<ConductorEvaluacion> conductorlist = service.getEvaluacionConductores();

        ArrayList<JSONObject> resConductorVehiculo = new ArrayList();

        for (int i = 0; i < conductorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            ConductorEvaluacion evaluacion = conductorlist.get(i);
            obj.put("evaluacion_id", evaluacion.getEvaluacion_id());
            obj.put("conductor_id", evaluacion.getConductor_id());
            obj.put("conductor_nombre", evaluacion.getConductor_nombre());
            obj.put("conductor_apellido", evaluacion.getConductor_apellido());
            obj.put("evaluador_id", evaluacion.getEvaluador_id());
            obj.put("evaluador_nombre", evaluacion.getEvaluador_nombre());
            obj.put("evaluador_apellido", evaluacion.getEvaluador_apellido());
            obj.put("notaExamTeo", evaluacion.getEvaluacion_notaExamTeo());
            obj.put("notaExamPrac", evaluacion.getEvaluacion_notaExamPrac());

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
            String evaluador = (String) payload.get("evaluador");
            Double notaExamTeo = ((Long) payload.get("notaExamTeo")).doubleValue();
            Double notaExamPrac = ((Long) payload.get("notaExamPrac")).doubleValue();

            System.out.println(conductor + evaluador + notaExamTeo + notaExamPrac);

            boolean rs = service.createEvaluacionConductor(conductor, evaluador, notaExamTeo, notaExamPrac);

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiConductorEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//////

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
        boolean res = service.deleteEvaluacionConductor(id);

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

            String conductor = (String) payload.get("conductor");
            String evaluador = (String) payload.get("evaluador");
            Double notaExamTeo = ((Long) payload.get("notaExamTeo")).doubleValue();
            Double notaExamPrac = ((Long) payload.get("notaExamPrac")).doubleValue();
            String id = (String) payload.get("id");

            boolean res = service.updateEvaluacionConductor(id, conductor, evaluador, notaExamTeo, notaExamPrac);

            JSONObject msj = new JSONObject();

            if (res) {
                msj.put("error", false);
                out.println(msj);
            } else {
                msj.put("error", true);
                out.println(msj);
            }

        } catch (ParseException ex) {
            Logger.getLogger(ApiConductorVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
