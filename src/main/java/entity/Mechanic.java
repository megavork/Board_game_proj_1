package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "mechanics_game" )
public class Mechanic {
    @Id
    @Column(name = "idMechanics", length = 15)
    private String idMechanics;
    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idMechanicForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForMech"))
    private List<Game> games;

    public String getIdMechanics() {
        return idMechanics;
    }

    public void setIdMechanics(String id) {
        this.idMechanics = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mechanic() {
    }

    public Mechanic(String idMechanics, String name) {
        this.idMechanics = idMechanics;
        this.name = name;
    }
}
