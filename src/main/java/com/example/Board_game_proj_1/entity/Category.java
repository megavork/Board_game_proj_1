package com.example.Board_game_proj_1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "board_game_sch", name = "categories_game")
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "idCategories", length = 15)
    String idCategories;

    @Column(name = "name")
    String name;
/*
    @Transient
    @ManyToMany(mappedBy="depend_game_category",fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name="depend_game_category",
            joinColumns=@JoinColumn (name="idCategoryForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForCateg"))

 */
    @Transient
    @ManyToMany(mappedBy = "categories_game")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> gameList;

    public Category(String id, String name) {
        this.idCategories = id;
        this.name = name;
    }
}
