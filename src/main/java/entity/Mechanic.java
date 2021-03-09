package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "mechanics" )
public class Mechanic {
    @Id
    @Column(name = "idMechanics")
    private String idMechanics;
    @Column(name = "name")
    private String name;
/*
    @ManyToMany
    @JoinTable (name="dependency_game_mech",
            joinColumns=@JoinColumn (name="idMechanic"),
            inverseJoinColumns=@JoinColumn(name="idGame"))

 */
    @ManyToMany(mappedBy = "mechanicsTable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
