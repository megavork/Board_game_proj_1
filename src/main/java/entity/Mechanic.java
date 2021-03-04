package entity;

import javax.persistence.*;

@Entity
@Table(schema = "board_game_sch", name = "gamemechanics" )
public class Mechanic {
    @Id
    @Column(name = "idMechanics")
    private String idMechanics;
    @Column(name = "name")
    private String name;


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
