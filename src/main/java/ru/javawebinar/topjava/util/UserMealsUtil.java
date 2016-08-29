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
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        mealList = new ArrayList<>();
        for (int i = 0; i < 5000000; i++) {
            mealList.add(new UserMeal(LocalDateTime.of(2015, (int)Math.random()*5+1, (int) Math.random()*25+1, (int) (Math.random()*22+1),0), "Завтрак", 500));
        }
        long start = new Date().getTime();
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        long stop = new Date().getTime();
        System.out.println(stop-start);
        /*for (UserMealWithExceed umwe :
                filteredWithExceeded) {
            System.out.println(umwe.getDescription() + " " + umwe.getDateTime().toLocalTime() + " " + umwe.isExceed());
        }*/
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMeal> userMeals = new ArrayList<>();
        List<UserMealWithExceed> list = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumForDay = new HashMap<>();
        for (UserMeal meal :
                mealList) {
            if (caloriesSumForDay.containsKey(meal.getDateTime().toLocalDate())) {
                caloriesSumForDay.put(meal.getDateTime().toLocalDate(), caloriesSumForDay.get(meal.getDateTime().toLocalDate())+meal.getCalories());
            }
            else caloriesSumForDay.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            if (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                userMeals.add(meal);
            }
        }
        for (UserMeal meal :
                userMeals) {
            if (caloriesSumForDay.get(meal.getDateTime().toLocalDate())>caloriesPerDay) {
                list.add(new UserMealWithExceed(
                        meal.getDateTime(), meal.getDescription(), meal.getCalories(), true
                ));
            }
            else list.add(new UserMealWithExceed(
                    meal.getDateTime(), meal.getDescription(), meal.getCalories(), false
            ));
        }
        return list;
    }
}
