package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal, SecurityUtil.authUserId());
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
            log.info("save {}", meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (isBelong(meal.getId(), userId)) {
            log.info("update {}", meal);
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (isBelong(id, userId)) {
            log.info("delete {}", id);
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (isBelong(id, userId)) {
            log.info("get {}", id);
            return repository.get(id);
        }
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream().filter(meal -> meal.getUserId().equals(userId))
                .sorted((a, b) -> -a.getDate().compareTo(b.getDate()))
                .collect(Collectors.toList());
    }

    public Collection<Meal> getEstablishDate(LocalDate startDate, LocalDate endDate, int userId) {
        return getAll(userId).stream().filter(meal -> DateTimeUtil.isBetweenHalfOpenDate(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }

    public Collection<Meal> getEstablishTime(LocalTime startTime, LocalTime endDTime, int userId) {
        return getAll(userId).stream().filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endDTime))
                .collect(Collectors.toList());
    }

    public Collection<Meal> getEstablishDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endDTime, int userId) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenHalfOpenDate(meal.getDate(), startDate, endDate))
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endDTime))
                .collect(Collectors.toList());
    }

    private boolean isBelong(int id, int userId) {
        return repository.get(id).getUserId() == userId;
    }
}

