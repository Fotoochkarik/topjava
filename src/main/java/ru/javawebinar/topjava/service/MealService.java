package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Service
public class MealService {

    private final MealRepository repository;
    int userId = authUserId();

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal) {
        return (repository.save(meal, userId));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<MealTo> getAll() {
        return MealsUtil.getTos(repository.getAll(userId), authUserCaloriesPerDay());
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Collection<MealTo> getEstablishDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getTos(repository.getEstablishDateTime(startDate, endDate, startTime, endTime, userId), authUserCaloriesPerDay());
    }
}