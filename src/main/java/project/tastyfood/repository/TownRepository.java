package project.tastyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.tastyfood.model.entity.Town;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town,String> {
    Optional<Town> findByName(String name);
}
