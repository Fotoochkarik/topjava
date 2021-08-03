package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "profile/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealUIController extends AbstractMealController {

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Meal create(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam LocalDateTime dateTime,
                       @RequestParam String description,
                       @RequestParam int calories) {
        return super.create(new Meal(dateTime, description, calories));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

    @GetMapping("/filter")
    public List<MealTo> getBetween(@Nullable String startDate, @Nullable String startTime,
                                   @Nullable String endDate, @Nullable String endTime) {
        return super.getBetween(DateTimeUtil.parseLocalDate(startDate), DateTimeUtil.parseLocalTime(startTime),
                DateTimeUtil.parseLocalDate(endDate), DateTimeUtil.parseLocalTime(endTime));
    }
}
