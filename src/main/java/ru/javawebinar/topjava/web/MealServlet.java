package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.DAO;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private DAO db = new MealDao();

    public MealServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("list", db.getFilteredList());
        response.setContentType("text/html");
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);

        //response.sendRedirect("mealList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action==null) action = "";

        String dateT = req.getParameter("calories");
        if (dateT != null) System.out.println(dateT);

        String id = req.getParameter("id");
        if (id != null) System.out.println(id);


        if (action.equals("add")) {
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
            db.addMeal(new Meal(
                    dateTime,
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories"))
            ));
        }
        else if (action.equals("update")){
            int intID = Integer.parseInt(req.getParameter("id"));
            LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));

            db.updateMeal(intID, new Meal(
                    dateTime,
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories"))
            ));
            System.out.println("some updated (test)");
        }
        else if (action.equals("delete")){
            db.deleteMeal(Integer.parseInt(req.getParameter("id")));
            System.out.println("some deleted (test)");
        }
        updateView(req, resp);
    }
    private void updateView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", db.getFilteredList());
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }
}
