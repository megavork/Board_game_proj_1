package entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(schema = "board_game_sch", name = "categories_game")
@NoArgsConstructor
public class Category {
    @Id
    @Column(name = "idCategories", length = 15)
    String idCategories;

    @Column(name = "name")
    String name;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable (name="depend_game_category",
            joinColumns=@JoinColumn (name="idCategoryForGame"),
            inverseJoinColumns=@JoinColumn(name="idGamesForCateg"))
    private List<Game> gameList;

    public Category(String id, String name) {
        this.idCategories = id;
        this.name = name;
    }
}
