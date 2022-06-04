package ru.gbAleko.springLesson7HW2.repositories.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.gbAleko.springLesson7HW2.data.Product;

public class ProductSpecification {

    public static Specification<Product> scoreGreaterOrElseThan(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost));
    }

    public static Specification<Product> lessThanOrEqualTo(Integer cost){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost));
    }
}
