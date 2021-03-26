package com.example.Board_game_proj_1.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(schema = "board_game_sch", name = "mechanics_game" )
@NoArgsConstructor
public class Mechanic {
    @Id
    @Column(name = "idMechanics", length = 15)
    private String idMechanics;
    @Column(name = "name")
    private String name;
/*
    @Transient
    @ManyToMany(mappedBy="depend_game_mech",fetch = FetchType.EAGER)
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idMechanicForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForMech"))
 */
    @Transient
    @ManyToMany(mappedBy = "categories_game")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Game> games;

    public Mechanic(String idMechanics, String name) {
        this.idMechanics = idMechanics;
        this.name = name;
    }
}
