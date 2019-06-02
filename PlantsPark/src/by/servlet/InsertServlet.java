package by.servlet;

import by.park.entity.object.Plant;
import by.park.dao.service.PlantsListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name.equals("")) {
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        }else {
            Plant curPlant = PlantsListDao.getInstance().insert(name);
            request.setAttribute("curplant", curPlant);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("insert.jsp").forward(req, resp);
    }
}
