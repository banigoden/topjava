/*
https://www.baeldung.com/java-stream-reduce

 */

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealStream = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "breakfast", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "lanch", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "dinner", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "breakfast", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "lanch", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "dinner", 410)
        );

        List<UserMealWithExceed> mealsTo = filteredByCycles(mealStream, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        filteredByStreams(mealStream, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000).forEach(System.out::println);
    }

    public static List<UserMealWithExceed> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        final Map<LocalDate, Integer> sumOfCalories = new HashMap<>();
        meals.forEach( meal -> sumOfCalories.merge(meal.getDate(), meal.getCalories(), Integer::sum) );
        List<UserMealWithExceed> mealListExcees = new ArrayList<>();
        meals.forEach(userMeal -> {
           if (TimeUtil.isBetweenHalfOpen(userMeal.getTime(), startTime, endTime)){
            mealListExcees.add(createUserMealWithExceed(userMeal, sumOfCalories.get(userMeal.getDate()) > caloriesPerDay));
        }
       });
        return  mealListExcees;
    }
    private static UserMealWithExceed createUserMealWithExceed(UserMeal userMeal, boolean exceed){
        return new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),exceed);
    }

    public static List<UserMealWithExceed> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSum = meals.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream()
                .filter(sum -> TimeUtil.isBetweenHalfOpen(sum.getTime(), startTime, endTime))
                .map(sum -> createUserMealWithExceed(sum, caloriesSum.get(sum.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}