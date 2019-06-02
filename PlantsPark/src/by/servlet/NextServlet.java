package by.servlet;

import by.park.entity.object.Plant;
import by.park.dao.service.PlantsListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NextServlet", urlPatterns = "/next")
public class NextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plant curPlant = PlantsListDao.getInstance().getNext();

        request.setAttribute("curplant", curPlant);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
