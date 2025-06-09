package ma.enset.springmvc.web;

import ma.enset.springmvc.entities.Product;
import ma.enset.springmvc.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductRestController {
    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product findById(@PathVariable(name = "id") Long id) {
        return productRepository.findById(id).get();
    }

    @DeleteMapping("products/{id}")
    public void deleteById(@PathVariable(name = "id") Long id) {
         productRepository.deleteById(id);
    }


}
