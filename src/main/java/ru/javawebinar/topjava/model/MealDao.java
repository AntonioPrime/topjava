package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MealDao implements DAO {
    private Map<Integer, Meal> meals;

    public MealDao() {
        meals = new ConcurrentHashMap<>();
        for (Meal meal :
                MealsUtil.generateMeal()) {
            meals.put(meal.getId(), meal);
        }
    }

    public List<MealWithExceed> getFilteredList() {
        List<MealWithExceed> filteredWithExceeded = MealsUtil.getFilteredWithExceeded(new ArrayList<>(meals.values()), LocalTime.MIN, LocalTime.MAX, 2000);
        return filteredWithExceeded;
    }

    @Override
    public void addMeal(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(int id, Meal meal) {
        if (meals.containsKey(id)) {
            meals.put(id, meal);
        }
    }

    @Override
    public void deleteMeal(int id) {
        meals.remove(id);
    }


}
