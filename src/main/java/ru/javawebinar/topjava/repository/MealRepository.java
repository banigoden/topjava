package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.MealTo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface  MealRepository extends CrudRepository<MealTo, Long> {

}
