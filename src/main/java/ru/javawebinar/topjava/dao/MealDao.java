package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {

    Meal create(Meal meal);

    Meal findById(int id);

    Meal update(Meal meal);

    boolean delete(int id);

    List<Meal> getAll();
}
