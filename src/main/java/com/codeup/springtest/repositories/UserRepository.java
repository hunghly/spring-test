package com.codeup.springtest.repositories;

import com.codeup.springtest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    <S extends User> S save(S s);

    @Override
    void deleteById(Long aLong);

    User findUserByUsername(String username);


}
