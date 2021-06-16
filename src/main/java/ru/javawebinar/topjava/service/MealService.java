package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public List<MealTo> getAll(int userId) {
        return MealsUtil.getTos(repository.getAll(userId), authUserCaloriesPerDay());
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public List<MealTo> getFilterDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int userId) {
        if (startDate == null || endDate == null) {
            return MealsUtil.getFilteredTos(repository.getAll(userId), authUserCaloriesPerDay(), startTime, endTime);
        } else if (startTime == null || endTime == null) {
            return MealsUtil.getTos(repository.getFilterDate(startDate, endDate, userId), authUserCaloriesPerDay());
        } else if (startDate != null && endDate != null && startTime != null && endTime != null) {
            return MealsUtil.getFilteredTos(repository.getFilterDate(startDate, endDate, userId), authUserCaloriesPerDay(), startTime, endTime);
        } else {
            return MealsUtil.getTos(repository.getAll(userId), authUserCaloriesPerDay());
        }
    }
}