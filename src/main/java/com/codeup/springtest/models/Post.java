package com.codeup.springtest.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name="posts")
public class Post {

    // Assigning the primary key and the automatic number generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Assigning the columns
    @Column(nullable = false)
    @NotBlank(message = "Posts must have a title")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Posts must have a body")
    @Size(min = 3, message = "body must be at least 3 characters long.")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}

    public Post(long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
