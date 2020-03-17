package com.codeup.springtest.repositories;

import com.codeup.springtest.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {

}
