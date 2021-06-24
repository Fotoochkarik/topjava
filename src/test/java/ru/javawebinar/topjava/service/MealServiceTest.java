package ru.javawebinar.topjava.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal created = service.create(MealTestData.getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, service.get(referenceMeal.getId(), USER_ID).getDateTime(),
                        "Duplicate Meal", 550), USER_ID));
    }

    @Test
    public void delete() {
        service.delete(referenceMeal.getId(), USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(referenceMeal.getId(), USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void get() {
        Meal meal = service.get(referenceMeal.getId(), USER_ID);
        MealTestData.assertMatch(meal, MealTestData.referenceMeal);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        MealTestData.assertMatch(service.get(referenceMeal.getId(), USER_ID), MealTestData.getUpdated());
    }

    @Test
    public void updateNotFound(){
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        assertThrows(NotFoundException.class, ()-> service.get(referenceMeal.getId(), ADMIN_ID));
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID).stream()
                .sorted(Comparator.comparing(Meal::getId).reversed())
                .collect(Collectors.toList());
        MealTestData.assertMatch(all, MealTestData.mealsUser);
    }

    @Test
    public void getBetween(){
        List<Meal> between = service.getBetweenInclusive(mealUser.getDate(), mealUser5.getDate(), USER_ID).stream()
                .sorted(Comparator.comparing(Meal::getId).reversed())
                .collect(Collectors.toList());
        MealTestData.assertMatch(between, isBetween(mealsUser, mealUser.getDate(), mealUser5.getDate()));
    }
}
