package ru.gbAleko.springLesson7HW2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gbAleko.springLesson7HW2.data.Product;
import ru.gbAleko.springLesson7HW2.exceptions.AppError;
import ru.gbAleko.springLesson7HW2.repositories.ProductRepository;
import ru.gbAleko.springLesson7HW2.repositories.specification.ProductSpecification;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer p, Integer maxCost, Integer minCost){
        Specification<Product> spec = Specification.where(null);

        if(minCost != null){
            spec = spec.and(ProductSpecification.scoreGreaterOrElseThan(minCost));
        }
        if(maxCost != null){
            spec = spec.and(ProductSpecification.lessThanOrEqualTo(maxCost));
        }
        return productRepository.findAll(spec, PageRequest.of(p -1, 10));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

//    public void addProduct(String title, int cost) {
//        productRepository.save(new Product(title, cost));
//    }
public ResponseEntity<?> addProduct(Product product) {
    if(productRepository.existsProductByTitle(product.getTitle())){
        return new ResponseEntity<>(new AppError(HttpStatus.CONFLICT.value(), "Product is already"), HttpStatus.CONFLICT);
    } else {
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> findBiRangeCost(Integer min, Integer max) {
        return productRepository.findAllByCostBetween(min, max);
    }

    public Product findByTitle(String title) {
        return productRepository.findProductByTitle(title).orElseThrow();
    }

    public List<Product> findCostLessThan(Integer max) {
        return productRepository.findCostLessThan(max);
    }

    public List<Product> findCostMoreThan(Integer min) {
        return productRepository.findCostMoreThan(min);
    }

}
