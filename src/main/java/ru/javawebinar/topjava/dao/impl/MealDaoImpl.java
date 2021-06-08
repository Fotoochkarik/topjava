package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class MealDaoImpl implements MealDao {
    private static final ConcurrentMap<Integer, Meal> mealConcurrentMap = new ConcurrentHashMap<>();
    private static int count = 1;
    private static ReentrantLock counterLock = new ReentrantLock(true);

    private static void incrementCounter() {
        counterLock.lock();
        try {
            count++;
        } finally {
            counterLock.unlock();
        }
    }

    @Override
    public boolean create(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(count);
            mealConcurrentMap.put(count, meal);
            incrementCounter();
        }
        return true;
    }

    @Override
    public Meal findById(int id) {
        return mealConcurrentMap.get(id);
    }

    @Override
    public Meal update(int id, Meal meal) {
        mealConcurrentMap.remove(id);
        meal.setId(id);
        return mealConcurrentMap.put(id, meal);
    }

    @Override
    public Meal delete(int id) {
        return mealConcurrentMap.remove(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealConcurrentMap.values());
    }
}
