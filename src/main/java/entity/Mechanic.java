package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToMany
    @JoinTable (name="depend_game_mech",
            joinColumns=@JoinColumn (name="idMechanicForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForMech"))
    private List<Game> games;

    public Mechanic(String idMechanics, String name) {
        this.idMechanics = idMechanics;
        this.name = name;
    }
}
