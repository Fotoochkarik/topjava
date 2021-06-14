package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Collection<MealTo> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public void update(Meal meal) {
        log.info("update {}", meal);
        service.update(meal);
    }

    public Collection<MealTo> getEstablishDateTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        return service.getEstablishDateTime(startDate, endDate, startTime, endTime);
    }
}