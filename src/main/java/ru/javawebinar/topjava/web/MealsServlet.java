package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemory;
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
    private final MealDao mealDao = new MealDaoInMemory();

    public MealsServlet() {
        List meals = new ArrayList<>(Arrays.asList(
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        ));
        meals.forEach(meal -> mealDao.save((Meal) meal));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Meal> meals = new ArrayList<>(mealDao.getAll());
        String action = request.getParameter("action");

        if (action == null) {
            log.debug("redirect to meals");
            request.setAttribute("meals", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
            request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealDao.delete(mealId);
            log.debug("redirect to meals");
            response.sendRedirect("meals");
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDao.findById(mealId);
            log.debug("redirect to editMeal");
            request.setAttribute("meal", meal);
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        } else if (action.equalsIgnoreCase("insert")) {
            log.debug("redirect to editMeal");
            request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
        } else {
            log.debug("redirect to meals");
            response.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String mealId = request.getParameter("mealId");
        Meal meal = new Meal(mealId.isEmpty() ? null : Integer.valueOf(mealId), dateTime, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")));

        mealDao.save(meal);
        log.debug("redirect to meals");
        response.sendRedirect("meals");
    }
}
