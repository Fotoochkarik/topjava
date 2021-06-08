package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.impl.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);
    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    MealDao mealDao = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Meal> meals = mealDao.getAllMeals();
        String forward = "";
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX));
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("delete")) {
            log.debug("redirect to meals");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.delete(mealId);
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX));
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("redirect to editMeal");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDao.findById(mealId);
            request.setAttribute("meal", meal);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("meals")) {
            log.debug("redirect to meals");
            forward = LIST_MEAL;
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX));
        } else {
            log.debug("redirect to editMeal");
            forward = INSERT_OR_EDIT;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String dateString = request.getParameter("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(dateString);

        Meal meal = new Meal(dateTime, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));
        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            mealDao.create(meal);
        } else {
            mealDao.update(Integer.parseInt(mealId), meal);
        }
        request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAllMeals(), LocalTime.MIN, LocalTime.MAX));
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}
