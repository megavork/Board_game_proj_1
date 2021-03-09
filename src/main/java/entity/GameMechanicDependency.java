package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "board_game_sch", name = "dependency_game_mech")
public class GameMechanicDependency implements Serializable {
    @Id
    @Column(name = "idGame", length = 15)
    String idGame;
    @Id
    @Column(name = "idMechanic", length = 15)
    String idMechanic;

    public GameMechanicDependency() {
    }

    public GameMechanicDependency(String idGame, String idMechanic) {
        this.idGame = idGame;
        this.idMechanic = idMechanic;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getIdMechanics() {
        return idMechanic;
    }

    public void setIdMechanics(String idMechanic) {
        this.idMechanic = idMechanic;
    }
}
