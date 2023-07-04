package org.wirehood.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.wirehood.productservice.model.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
