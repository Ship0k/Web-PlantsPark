package by.servlet;

import by.park.entity.object.Plant;
import by.park.dao.service.PlantsListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/byId")
public class ByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            Plant curPlant = PlantsListDao.getInstance().byId(id);
            request.setAttribute("curplant", curPlant);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }catch (NumberFormatException e) {
            response.sendRedirect("http://localhost:8080/home");
        }
    }
}
