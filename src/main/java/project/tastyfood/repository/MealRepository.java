package project.tastyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tastyfood.model.entity.Meal;


import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal,String> {
    Optional<Meal> findByName(String name);
    List<Meal> findAllByRestaurant_Id(String id);
}
