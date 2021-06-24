package ru.javawebinar.topjava.web.meal;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.javawebinar.topjava.MealTestData.referenceMeal;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

public class InMemoryMealRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static MealRestController controller;
    private static InMemoryMealRepository repository;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app-test.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(MealRestController.class);
        repository = appCtx.getBean(InMemoryMealRepository.class);
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
    }

    @Before
    public void setup() {
        repository.init();
    }

    @Test
    public void get() {
    }

    @Test
    public void getNotFound() {
    }

    @Test
    public void delete() {
        controller.delete(referenceMeal.getId());
        Assert.assertNull(repository.get(referenceMeal.getId(), USER_ID));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }

    @Test
    public void getAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void updateNotFound() {
    }
}