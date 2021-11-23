/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package routes;

import java.io.IOException;
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
@WebServlet(name = "pais", urlPatterns = {"/informaciongeneral/pais"})
public class pais extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("usuario");

        if (user != null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            request.getRequestDispatcher("/view/pais.jsp").forward(request, response);

        } else {

            response.sendRedirect("/login");

        }
    }

}
