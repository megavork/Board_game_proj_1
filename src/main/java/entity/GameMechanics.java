package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamemechanics")
public class GameMechanics {
    @Id
    @Getter
    String id;
    @Getter
    String name;
    @Getter
    String url;

    public GameMechanics() {
    }

    public GameMechanics(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public GameMechanics(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
