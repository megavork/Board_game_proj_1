package com.example.Board_game_proj_1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(schema = "board_game_sch", name = "game_objects" )
@NoArgsConstructor
public class Game {
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

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idGamesForMech"),
            inverseJoinColumns=@JoinColumn(name="idMechanicForGame"))
    private List<Mechanic> mechanicsTable;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable (name="depend_game_category",
            joinColumns=@JoinColumn (name="idGamesForCateg"),
            inverseJoinColumns=@JoinColumn(name="idCategoryForGame"))
    private List<Category> categoryTable;

}
