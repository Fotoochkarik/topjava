package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.impl.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealsServlet extends HttpServlet {
    private static final Logger log = getLogger(MealsServlet.class);
    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private final MealDao mealDao = new MealDaoInMemoryImpl();

    public MealsServlet() {
        List meals = new ArrayList<>(Arrays.asList(
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
        meals.forEach(meal -> mealDao.create((Meal)meal));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Meal> meals = mealDao.getAll();
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.getCaloriesPerDay()));
            request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            log.debug("redirect to meals");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.delete(mealId);
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.getCaloriesPerDay()));
            response.sendRedirect("meals");
        } else if (action.equalsIgnoreCase("edit")) {
            log.debug("redirect to editMeal");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDao.findById(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        } else if (action.equalsIgnoreCase("meals")) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.getCaloriesPerDay()));
            request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        } else if (action.equalsIgnoreCase("insert")) {
            log.debug("redirect to editMeal");
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        } else {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.getCaloriesPerDay()));
            request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String dateString = request.getParameter("dateTime");
        LocalDateTime dateTime = LocalDateTime.parse(dateString);
        int mealId = Integer.parseInt(request.getParameter("mealId"));
        Meal meal = new Meal(mealId, dateTime, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));

        if (mealId <= 0) {
            mealDao.create(meal);
        } else {
            mealDao.update(meal);
        }
        request.setAttribute("meals", MealsUtil.filteredByStreams(mealDao.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.getCaloriesPerDay()));
        response.sendRedirect("meals");
    }
}
