package com.codeup.springtest.repositories;

import com.codeup.springtest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
