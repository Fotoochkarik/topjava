package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemory implements MealDao {
    private static final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private static final AtomicInteger countId = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(countId.incrementAndGet());
            mealsMap.put(meal.getId(), meal);
            return meal;
        }
        return mealsMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal findById(int id) {
        return mealsMap.get(id);
    }

    @Override
    public boolean delete(int id) {
        return mealsMap.remove(id) != null;
    }

    @Override
    public Collection<Meal> getAll() {
        return mealsMap.values();
    }
}
