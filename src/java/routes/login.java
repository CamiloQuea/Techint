/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routes;

import controller.UserServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import util.BCrypt;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    UserServiceImpl service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("usuario");

        if (user != null) {

            response.sendRedirect("/");

        } else {

            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("correo");

        String contra = request.getParameter("contra");

        User rs = service.verifyUser(email);

        boolean rspass = BCrypt.checkpw(contra, rs.getContraseña());

        if (rspass) {

            HttpSession sessionok = request.getSession();
            
            sessionok.setMaxInactiveInterval(86400);

            sessionok.setAttribute("usuario", rs);

            response.sendRedirect("/");

        } else {

            request.setAttribute("Error", "usuario o contraseñá incorrecta");

            request.getRequestDispatcher("view/login.jsp").forward(request, response);

        }

    }

}
