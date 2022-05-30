package ru.gbAleko.springLesson7HW2.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gbAleko.springLesson7HW2.data.Product;
import ru.gbAleko.springLesson7HW2.services.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/products/find/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/products/find_title/{title}")
    public Product getByTitle(@PathVariable String title) {
        return  productService.findByTitle(title);
    }

    @GetMapping("/products/delete/{id}")
    public  void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/products/deleteById")
    public void deleteProductById(@RequestParam Long id) { productService.deleteById(id);}

//    @PostMapping("/products/add")
//    public void addProduct(@RequestParam String title, @RequestParam int cost) {
//        productService.addProduct(title, cost);
//    }

    @GetMapping("products/between")
    public List<Product> findByRangeBetween(@RequestParam(defaultValue = "0") Integer min, @RequestParam(defaultValue = "100000") Integer max) {
        return productService.findBiRangeCost(min, max);
    }

    @GetMapping("/products/less")
    public  List<Product> getLowThenMax(@RequestParam(defaultValue = "100000") Integer max) {
        return productService.findCostLessThan(max);
    }

    @GetMapping("/products/more")
    public  List<Product> getHighThenMax(@RequestParam(defaultValue = "0") Integer min) {
        return productService.findCostMoreThan(min);
    }

    @GetMapping("products/between_page")
    public Page<Product> getAllStudents(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                        @RequestParam(name = "min_Cost", required = false) Integer minCost,
                                        @RequestParam(name = "max_Cost", required = false) Integer maxCost
    ) {
        if(page < 1){
            page = 1;
        }
        return productService.find(page, minCost, maxCost);
    }
}
