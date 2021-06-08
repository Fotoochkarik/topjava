package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    boolean create(Meal meal);

    Meal findById(int id);

    Meal update(int id, Meal meal);

    Meal delete(int id);

    List<Meal> getAllMeals();
}
