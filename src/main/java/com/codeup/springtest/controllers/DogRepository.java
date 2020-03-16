package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

interface DogRepository extends JpaRepository<Dog, Long> {

}
