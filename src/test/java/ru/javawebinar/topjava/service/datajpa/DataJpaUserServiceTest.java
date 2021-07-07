package ru.javawebinar.topjava.service.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {
//    @Autowired
//    DataJpaUserRepository repository;

//    @Test
//    public void getMeals() {
//        User user = repository.getWithMeals(USER_ID);
//        UserTestData.MATCHER.assertMatch(user, UserTestData.user);
//        MealTestData.MATCHER.assertMatch(user.getMeals(), meals);
//    }

}
