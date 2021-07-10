package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.MealTestData.meals;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {

    @Test
    public void getUserWithMils() {
        User user = service.getWithMils(USER_ID);
        UserTestData.MATCHER.assertMatch(user, UserTestData.user);
        MealTestData.MATCHER.assertMatch(user.getMeals(), meals);
    }

    @Test

    public void getWithMilsGraph() {
        User user = service.getWithMilsGraph(USER_ID);
        UserTestData.MATCHER.assertMatch(user, UserTestData.user);
        MealTestData.MATCHER.assertMatch(user.getMeals(), meals);
    }
}
