package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "board_game_sch", name = "gamecategories" )
public class Category {
    @Id
    @Column(name = "idCategories")
    String idCategories;
    @Column(name = "name")
    String name;

    public String getId() {
        return idCategories;
    }

    public void setId(String id) {
        this.idCategories = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category() {
    }

    public Category(String id, String name) {
        this.idCategories = id;
        this.name = name;
    }
}
