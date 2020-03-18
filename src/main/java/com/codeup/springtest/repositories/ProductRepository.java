package com.codeup.springtest.repositories;

import com.codeup.springtest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findProductByNameOrDescription(String name, String description);
//
//
//    List<Product> findByNameOrDescriptionContaining(String name, String description);

    @Query("select p from Product p where p.name like %?1% or p.description like %?1%")
    List<Product> searchByNameOrDescription(String query);
}
