package ru.javawebinar.topjava.model;

import java.util.List;

/**
 * Created by AntonioPrime on 15.09.2016.
 */
public interface DAO {
    void addMeal(Meal meal);
    void updateMeal(int id, Meal meal);
    void deleteMeal(int id);

    List<MealWithExceed> getFilteredList();
}
