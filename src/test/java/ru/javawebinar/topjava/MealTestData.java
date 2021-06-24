package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.javawebinar.topjava.util.DateTimeUtil.atStartOfNextDayOrMax;

public class MealTestData {

    public static Meal referenceMeal = new Meal(100, LocalDateTime.of(2021, Month.FEBRUARY, 1, 13, 13),
            "Reference meal USER", 350);

    public static final Meal mealUser = new Meal(100002, LocalDateTime.of(2021, Month.FEBRUARY, 1, 7, 0),
            "Breakfast USER", 350);
    public static final Meal mealUser1 = new Meal(100003, LocalDateTime.of(2021, Month.FEBRUARY, 1, 12, 0),
            "Lunch USER", 550);
    public static final Meal mealUser2 = new Meal(100004, LocalDateTime.of(2021, Month.FEBRUARY, 1, 21, 10),
            "Dinner USER", 350);
    public static final Meal mealUser3 = new Meal(100005, LocalDateTime.of(2021, Month.FEBRUARY, 2, 6, 0),
            "Breakfast USER", 400);
    public static final Meal mealUser4 = new Meal(100006, LocalDateTime.of(2021, Month.FEBRUARY, 2, 12, 30),
            "Lunch USER", 700);
    public static final Meal mealUser5 = new Meal(100007, LocalDateTime.of(2021, Month.FEBRUARY, 2, 19, 30),
            "Dinner USER", 600);
    public static final Meal mealUser6 = new Meal(100008, LocalDateTime.of(2021, Month.FEBRUARY, 3, 6, 0),
            "Breakfast USER", 360);
    public static final Meal mealUser7 = new Meal(100009, LocalDateTime.of(2021, Month.FEBRUARY, 3, 12, 30),
            "Lunch USER", 550);
    public static final Meal mealUser8 = new Meal(100010, LocalDateTime.of(2021, Month.FEBRUARY, 3, 19, 30),
            "Dinner USER", 500);
    public static final Meal mealUser9 = new Meal(100011, LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0),
            "Еда на граничное значение USER", 100);

    public static final Meal mealAdmin1 = new Meal(100012, LocalDateTime.of(2021, Month.FEBRUARY, 1, 7, 0),
            "Breakfast ADMIN", 300);
    public static final Meal mealAdmin2 = new Meal(100013, LocalDateTime.of(2021, Month.FEBRUARY, 1, 13, 15),
            "Lunch ADMIN", 600);
    public static final Meal mealAdmin3 = new Meal(100014, LocalDateTime.of(2021, Month.FEBRUARY, 1, 20, 0),
            "Dinner ADMIN", 550);
    public static final Meal mealAdmin4 = new Meal(100015, LocalDateTime.of(2021, Month.FEBRUARY, 2, 6, 0),
            "Breakfast ADMIN", 400);
    public static final Meal mealAdmin5 = new Meal(100016, LocalDateTime.of(2021, Month.FEBRUARY, 2, 12, 30),
            "Lunch ADMIN", 700);
    public static final Meal mealAdmin6 = new Meal(100017, LocalDateTime.of(2021, Month.FEBRUARY, 2, 19, 30),
            "Dinner ADMIN", 600);
    public static final Meal mealAdmin7 = new Meal(100018, LocalDateTime.of(2021, Month.FEBRUARY, 3, 6, 0),
            "Breakfast ADMIN", 360);
    public static final Meal mealAdmin8 = new Meal(100019, LocalDateTime.of(2021, Month.FEBRUARY, 3, 12, 30),
            "Lunch ADMIN", 550);
    public static final Meal mealAdmin9 = new Meal(100020, LocalDateTime.of(2021, Month.FEBRUARY, 3, 19, 30),
            "Dinner ADMIN", 500);
    public static final Meal mealAdmin10 = new Meal(100021, LocalDateTime.of(2021, Month.JANUARY, 31, 0, 0),
            "Еда на граничное значение ADMIN", 100);

    public static final List<Meal> mealsAdmin = Arrays.asList(mealAdmin1, mealAdmin2, mealAdmin3, mealAdmin4,
            mealAdmin5, mealAdmin6, mealAdmin7, mealAdmin8, mealAdmin9, mealAdmin10).stream()
            .sorted(Comparator.comparing(Meal::getId))
            .collect(Collectors.toList());

    public static final List<Meal> mealsUser = Stream.of(mealUser, mealUser1, mealUser2, mealUser3, mealUser4,
            mealUser5, mealUser6, mealUser7, mealUser8, mealUser9, referenceMeal)
            .sorted(Comparator.comparing(Meal::getId).reversed())
            .collect(Collectors.toList());

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
        assertThat(actual)
//                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(referenceMeal.getId(), referenceMeal.getDateTime(),
                referenceMeal.getDescription(), referenceMeal.getCalories());
        updated.setDateTime(LocalDateTime.of(2021, Month.JUNE, 1, 10, 10));
        updated.setDescription("new description");
        updated.setCalories(100);
        return updated;
    }

    public static List<Meal> isBetween(List<Meal> list, LocalDate startDate, LocalDate endDate){
        return list.stream()
                .filter(meal-> Util.isBetweenHalfOpen(meal.getDateTime(), atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate)))
                .sorted(Comparator.comparing(Meal::getId).reversed())
                .collect(Collectors.toList());
    }
}
