package com.codeup.springtest.controllers;

import com.codeup.springtest.models.Post;
import com.codeup.springtest.models.User;
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
    @Query("update Post p set p.title = ?1, p.body = ?2, p.user = ?3 where p.id = ?4")
    void updateById(String title, String body, User user, long adId);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Post post);
}
