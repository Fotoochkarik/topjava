package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static Meal testMeal = new Meal(100, LocalDateTime.of(2021, Month.FEBRUARY, 01, 13, 13), "Test meal USER", 350);

    private static Meal mealUser = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 07, 00), "Breakfast USER", 350);
    private static Meal mealUser1 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 12, 00), "Lunch USER", 550);
    private static Meal mealUser2 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 21, 10), "Dinner USER", 350);
    private static Meal mealUser3 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 06, 00), "Breakfast USER", 400);
    private static Meal mealUser4 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 12, 30), "Lunch USER", 700);
    private static Meal mealUser5 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 19, 30), "Dinner USER", 600);
    private static Meal mealUser6 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 06, 00), "Breakfast USER", 360);
    private static Meal mealUser7 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 12, 30), "Lunch USER", 550);
    private static Meal mealUser8 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 19, 30), "Dinner USER", 500);
    private static Meal mealUser9 = new Meal(LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0), "Еда на граничное значение USER", 100);

    private static Meal mealAdmin1 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 07, 00), "Breakfast ADMIN", 300);
    private static Meal mealAdmin2 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 13, 15), "Lunch ADMIN", 600);
    private static Meal mealAdmin3 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 01, 20, 00), "Dinner ADMIN", 550);
    private static Meal mealAdmin4 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 06, 00), "Breakfast ADMIN", 400);
    private static Meal mealAdmin5 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 12, 30), "Lunch ADMIN", 700);
    private static Meal mealAdmin6 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 02, 19, 30), "Dinner ADMIN", 600);
    private static Meal mealAdmin7 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 06, 00), "Breakfast ADMIN", 360);
    private static Meal mealAdmin8 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 12, 30), "Lunch ADMIN", 550);
    private static Meal mealAdmin9 = new Meal(LocalDateTime.of(2021, Month.FEBRUARY, 03, 19, 30), "Dinner ADMIN", 500);
    private static Meal mealAdmin10 = new Meal(LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0), "Еда на граничное значение USER", 100);

    public static final List<Meal> mealsAdmin = Arrays.asList(mealAdmin1, mealAdmin2, mealAdmin3, mealAdmin4, mealAdmin5, mealAdmin6, mealAdmin7, mealAdmin8, mealAdmin9, mealAdmin10);

    public static final List<Meal> mealsUser = Arrays.asList(mealUser, mealUser1, mealUser2, mealUser3, mealUser4, mealUser5, mealUser6, mealUser7, mealUser8, mealUser9);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JUNE, 20, 07, 00), "Test Meal", 550);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("id").isEqualTo(expected);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(100, LocalDateTime.of(2021, Month.FEBRUARY, 01, 13, 13), "Test meal USER", 350);
        updated.setDateTime(LocalDateTime.of(2021, Month.JUNE, 01, 10, 10));
        updated.setDescription("new description");
        updated.setCalories(100);
        return updated;
    }
}
