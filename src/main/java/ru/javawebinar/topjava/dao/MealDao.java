package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealDao {

    Meal save(Meal meal);

    Meal findById(int id);

    boolean delete(int id);

    Collection<Meal> getAll();
}
