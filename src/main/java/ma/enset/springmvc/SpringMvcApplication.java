package ma.enset.springmvc;

import ma.enset.springmvc.entities.Product;
import ma.enset.springmvc.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start (ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("PC1")
                    .price(2300)
                    .quantity(7)
                    .build());
            productRepository.save(Product.builder()
                    .name("PC2")
                    .price(5670)
                    .quantity(35)
                    .build());
            productRepository.save(Product.builder()
                    .name("PC3")
                    .price(11250)
                    .quantity(17)
                    .build());
            productRepository.findAll().forEach(p->
                    System.out.println(p.toString()));
        };
    }
}
