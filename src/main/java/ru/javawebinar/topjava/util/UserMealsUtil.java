/*
https://www.baeldung.com/java-stream-reduce

 */

package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {

    public static void main(String[] args) {
        Stream<UserMeal> mealStream = Stream.of(
                new UserMeal(LocalDateTime.of(2021, Month.FEBRUARY, 1,10,0), "breakfast", 500),
                new UserMeal(LocalDateTime.of(2021, Month.FEBRUARY, 1,13,0), "lanch", 500),
                new UserMeal(LocalDateTime.of(2021, Month.FEBRUARY, 1,18,0), "dinner", 500),
                new UserMeal(LocalDateTime.of(2021, Month.FEBRUARY, 1,10,0), "breakfast", 500)
        );
        getFilteredMilsWithExceed(mealStream);
//        toLocalDate();
//        toLocalTime();
    }
    private static UserMealWithExceed createUserMealWithExceed(UserMeal userMeal, boolean exceed){
        return new UserMealWithExceed(userMeal.getDateTime(),userMeal.getDescription(),userMeal.getCalories(),exceed);
    }

    public static Stream<UserMealWithExceed> getFilteredMilsWithExceed(Stream<UserMeal>mealList){
        return null;
    }
}