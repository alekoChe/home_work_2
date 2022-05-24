package ru.gbAleko.springLesson7HW2.services;

import org.springframework.stereotype.Service;
import ru.gbAleko.springLesson7HW2.data.Product;
import ru.gbAleko.springLesson7HW2.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void addProduct(String title, int cost) {
        productRepository.save(new Product(title, cost));
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
