/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@WebServlet(name = "tipo_infraccion", urlPatterns = {"/tipoinfraccion"})
public class tipo_infraccion extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        User user = (User) request.getSession().getAttribute("usuario");

        if (user != null) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            request.getRequestDispatcher("view/tipo_infraccion.jsp").forward(request, response);

        } else {

            response.sendRedirect("/login");

        }
        
    }

  


}
