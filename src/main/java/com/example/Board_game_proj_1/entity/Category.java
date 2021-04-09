package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.CategoryDto;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "categories_game")
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @Column(name = "idCategories", length = 15)
    String idCategories;
    @Column(name = "name")
    String name;

    //@Transient
    //@ManyToMany(mappedBy="categories_game",fetch = FetchType.EAGER)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name="depend_game_category",
            joinColumns=@JoinColumn (name="idCategoryForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForCateg"))
    List<Game> gameList = new ArrayList<>();

    public Category(String id, String name) {
        this.idCategories = id;
        this.name = name;
    }

    public CategoryDto fromEntityToDto() {
        CategoryDto categoryDto = new CategoryDto();

        return categoryDto;
    }

    public String getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(String idCategories) {
        this.idCategories = idCategories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
