package com.codeup.springtest.repositories;

import com.codeup.springtest.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1% or p.description like %?1%")
    Page<Product> searchByNameOrDescription(String name, Pageable pageable);

    @Override
    Page<Product> findAll(Pageable pageable);

}
