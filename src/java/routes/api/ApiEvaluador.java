package routes.api;

import controller.UserServiceImpl;
import ServicesInterfaces.UserService;
import controller.EvaluadorServiceImpl;
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
import model.Evaluador;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import util.BCrypt;
import util.http;

@WebServlet(name = "ApiEvaluador", urlPatterns = {"/api/evaluador"})
public class ApiEvaluador extends HttpServlet {

    EvaluadorServiceImpl service = new EvaluadorServiceImpl();

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
        ArrayList<Evaluador> evaluadorlist = service.getEvaluadores();

        ArrayList<JSONObject> resEvaluador = new ArrayList();

        for (int i = 0; i < evaluadorlist.size(); i++) {
            JSONObject obj = new JSONObject();
            Evaluador evaluador = evaluadorlist.get(i);

            obj.put("id", evaluador.getId());
            obj.put("nombre", evaluador.getNombre());
            obj.put("apellido", evaluador.getApellido());
            obj.put("empresa_id", evaluador.getEmpresa_id());
            obj.put("empresa_name", evaluador.getEmpresa_name());

            resEvaluador.add(obj);

        }

        out.println(resEvaluador);

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
            String empresa_id = (String) payload.get("empresa_id");

            boolean rs = service.createEvaluador(empresa_id, nombre, apellido);

            JSONObject res = new JSONObject();

            if (rs) {

                res.put("error", false);
                out.println(res);

            } else {
                res.put("error", true);
                out.println(res);
            }
        } catch (ParseException ex) {
            Logger.getLogger(ApiEvaluador.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean res = service.deleteEvaluador(id);

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

            String user_id = (String) payload.get("id");
            String nombre = (String) payload.get("nombre");
            String apellido = (String) payload.get("apellido");
            String empresa_id = (String) payload.get("empresa_id");

            boolean res = service.updateEvaluador(user_id, empresa_id, nombre, apellido);

            JSONObject msj = new JSONObject();

            if (res) {
                msj.put("error", false);
                out.println(msj);
            } else {
                msj.put("error", true);
                out.println(msj);
            }

        } catch (ParseException ex) {
            Logger.getLogger(ApiEvaluador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
