package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.CategoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "categories_game")
public class Category implements Serializable {
    @Id
    @Column(name = "idCategories", length = 15)
    String idCategories;
    @Column(name = "name")
    String name;

    public Category() {
    }

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

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String object = new String("null");
        try {
            object = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return object;
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

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public CategoryDto toCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setIdCategories(this.idCategories);
        categoryDto.setName(this.name);
        categoryDto.setGameList(this.gameList);
        return categoryDto;
    }
}
