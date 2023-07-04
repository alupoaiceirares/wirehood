package org.wirehood.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.wirehood.productservice.repository.ProductRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = ProductRepository.class)
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
