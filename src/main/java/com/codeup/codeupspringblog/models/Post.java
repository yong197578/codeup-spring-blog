package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, columnDefinition = "VARCHAR(100)") //columDefinition = "VARCHAR(100)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT") //columnDefinition = "TEXT"
    private String body;

    @ManyToOne
    @JoinColumn (name="userId")
    private User user;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_categories",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    private List<PostCategories> categories;


}
