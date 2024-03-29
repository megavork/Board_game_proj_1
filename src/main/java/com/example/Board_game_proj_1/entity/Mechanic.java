package com.example.Board_game_proj_1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(schema = "board_game_sch", name = "mechanics_game" )
@NoArgsConstructor
public class Mechanic implements Serializable {
    @Id
    @Column(name = "idMechanics", length = 15)
    private String idMechanics;
    @Column(name = "name")
    private String name;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idMechanicForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForMech"))
    @OrderBy(value = "average_user_rating DESC")
    List<Game> gameList = new ArrayList<>();

    public Mechanic(String idMechanics, String name) {
        this.idMechanics = idMechanics;
        this.name = name;
    }
}
