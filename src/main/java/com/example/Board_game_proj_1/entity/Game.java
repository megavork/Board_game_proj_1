package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.GameDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(schema = "board_game_sch", name = "game_objects" )
@NoArgsConstructor
public class Game implements Serializable {
    @Id
    @Column(name = "idGame", length = 15)
    String idGame;
    @Column(name = "name")
    String name;
    @Column(name = "year_published")
    int year_published;
    @Column(name = "min_players")
    int min_players;
    @Column(name = "max_players")
    int max_players;
    @Column(name = "min_playtime")
    int min_playtime;
    @Column(name = "max_playtime")
    int max_playtime;
    @Column(name = "min_age")
    int min_age;
    @Column(name = "description_preview", length = 3000)
    String description_preview;
    @Column(name = "thumb_url")
    String thumb_url;
    @Column(name = "image_url")
    String image_url;
    @Column(name = "price")
    float price;
    @Column(name = "discount")
    float discount;
    @Column(name = "average_user_rating")
    float average_user_rating;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idGamesForMech"),
            inverseJoinColumns=@JoinColumn(name="idMechanicForGame"))
    List<Mechanic> mechanicsTable = new ArrayList<>();

/*
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name="depend_game_category",
        joinColumns=@JoinColumn (name="idGamesForCateg"),
        inverseJoinColumns=@JoinColumn(name="idCategoryForGame"))

 */

    @ManyToMany(mappedBy = "gameList")
    List<Category> categoryTable = new ArrayList<>();



    public GameDto fromGameToGameDto() {
        GameDto gameDto = new GameDto();
        gameDto.setIdGame(this.getIdGame());
        gameDto.setName(this.getName());
        gameDto.setYear_published(this.getYear_published());
        gameDto.setMin_players(this.getMin_players());
        gameDto.setMax_players(this.getMax_players());
        gameDto.setMin_playtime(this.getMin_playtime());
        gameDto.setMax_playtime(this.getMax_playtime());
        gameDto.setMin_age(this.getMin_age());
        gameDto.setDescription_preview(this.getDescription_preview());
        gameDto.setThumb_url(this.getThumb_url());
        gameDto.setImage_url(this.getImage_url());
        gameDto.setPrice(this.getPrice());
        gameDto.setDiscount(this.getDiscount());
        gameDto.setAverage_user_rating(this.getAverage_user_rating());
        return gameDto;
    }
}
