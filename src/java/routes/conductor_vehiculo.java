/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Camilo
 */
@WebServlet(name = "conductor_vehiculo", urlPatterns = {"/conductor/vehiculo"})
public class conductor_vehiculo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setCharacterEncoding("utf-8");

        User user = (User) request.getSession().getAttribute("usuario");

        if (user != null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            request.getRequestDispatcher("/view/conductor_vehiculo.jsp").forward(request, response);

        } else {

            response.sendRedirect("/login");

        }

    }

}
