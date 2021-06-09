package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemoryImpl implements MealDao {
    private static final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private static final AtomicInteger countId = new AtomicInteger(1);
    private static final AtomicInteger convertorAtomic = new AtomicInteger();

    @Override
    public Meal create(Meal meal) {
        if (meal.getId() == 0) {
            meal.setId(countId.get());
            mealsMap.put(countId.getAndIncrement(), meal);
        }
        return meal;
    }

    @Override
    public Meal findById(int id) {
        convertorAtomic.set(id);
        return mealsMap.get(convertorAtomic.get());
    }

    @Override
    public Meal update(Meal meal) {
        convertorAtomic.set(meal.getId());
        return mealsMap.replace(convertorAtomic.get(), meal);
    }

    @Override
    public boolean delete(int id) {
        convertorAtomic.set(id);
        mealsMap.remove(convertorAtomic.get());
        return true;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealsMap.values());
    }
}
