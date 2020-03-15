package com.codeup.springtest.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository("PostRepository")
public interface PostRepository extends JpaRepository<Post, Long> {

    @Override
    List<Post> findAll();

    @Override
    <S extends Post> S save(S s);

    @Transactional
    @Modifying
    @Query("update Post p set p.title = ?1, p.body = ?2 where p.id = ?3")
    void updateById(String title, String body, long id);




    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Post post);
}
