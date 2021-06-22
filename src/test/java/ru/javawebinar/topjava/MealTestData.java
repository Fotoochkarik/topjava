package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final List<Meal> mealsUser = Arrays.asList(
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 07, 00), "Breakfast USER", 350),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 12, 00), "Lunch USER", 550),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 21, 10), "Dinner USER", 350),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 06, 00), "Breakfast USER", 400),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 12, 30), "Lunch USER", 700),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 19, 30), "Dinner USER", 600),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 06, 00), "Breakfast USER", 360),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 12, 30), "Lunch USER", 550),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 19, 30), "Dinner USER", 500)
    );

    public static final List<Meal> mealsAdmin = Arrays.asList(
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 07, 00), "Breakfast ADMIN", 300),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 13, 15), "Lunch ADMIN", 600),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 20, 00), "Dinner ADMIN", 550),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 06, 00), "Breakfast ADMIN", 400),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 12, 30), "Lunch ADMIN", 700),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 19, 30), "Dinner ADMIN", 600),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 06, 00), "Breakfast ADMIN", 360),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 12, 30), "Lunch ADMIN", 550),
            new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 19, 30), "Dinner ADMIN", 500)
    );

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JUNE, 20, 07, 00), "Test Meal", 550);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
