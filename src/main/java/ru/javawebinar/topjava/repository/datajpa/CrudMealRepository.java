package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findAllByUserId(int userId, Sort sort);

    Meal getByIdAndUserId(int id, int userId);

    @Transactional
    int deleteByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdAndDateTimeGreaterThanEqualAndDateTimeLessThan(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime, Sort sort);
}
