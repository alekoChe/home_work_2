package ru.gbAleko.springLesson7HW2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gbAleko.springLesson7HW2.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.id = :id")
    Optional<Product> findById(Long id);

    @Query("select p from Product p where p.title = :title")
    Optional<Product> findProductByTitle(String title);

    @Query("select p from Product p where p.cost < :maxCost")
    List<Product> findCostLessThan(Integer maxCost);

    @Query("select p from Product p where p.cost > :minCost")
    List<Product> findCostMoreThan(Integer minCost);
}
