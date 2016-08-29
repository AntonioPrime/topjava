package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

//        .toLocalDate();
//        .toLocalTime();
    }


    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMeal> filteredUserMeals = new ArrayList<>();
        List<UserMealWithExceed> finalList = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumForDay = new HashMap<>();
        mealList.stream()
                .peek(userMeal
                        -> {
                    LocalDate currentDate = userMeal.getDateTime().toLocalDate();
                    if (caloriesSumForDay.containsKey(currentDate)) {
                        caloriesSumForDay.put(userMeal.getDateTime().toLocalDate(), caloriesSumForDay.get(currentDate) + userMeal.getCalories());
                    } else caloriesSumForDay.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories());

                })
                .filter(userMeal
                        -> userMeal.getDateTime().toLocalTime().isAfter(startTime)
                        && userMeal.getDateTime().toLocalTime().isBefore(endTime))
                .forEach(filteredUserMeals::add);
        filteredUserMeals.forEach(userMeal
                -> finalList.add(new UserMealWithExceed(
                userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                caloriesSumForDay.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay
        )));
        return finalList;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededJava7(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMeal> userMeals = new ArrayList<>();
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumForDay = new HashMap<>();
        for (UserMeal meal :
                mealList) {
            if (caloriesSumForDay.containsKey(meal.getDateTime().toLocalDate())) {
                caloriesSumForDay.put(meal.getDateTime().toLocalDate(), caloriesSumForDay.get(meal.getDateTime().toLocalDate()) + meal.getCalories());
            } else caloriesSumForDay.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            if (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                userMeals.add(meal);
            }
        }
        for (UserMeal meal :
                userMeals) {
            if (caloriesSumForDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay) {
                list.add(new UserMealWithExceed(
                        meal.getDateTime(), meal.getDescription(), meal.getCalories(), true
                ));
            } else list.add(new UserMealWithExceed(
                    meal.getDateTime(), meal.getDescription(), meal.getCalories(), false
            ));
        }
        return list;
    }
}
