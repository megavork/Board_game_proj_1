package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "games" )
public class Game {
    @Id
    @Column(name = "idGame", length = 15)
    String idGame;
    @Column(name = "name")
    String name;
    @Column(name = "year_published")
    int year_published;
    @Column(name = "min_players")
    int min_players;
    @Column(name = "max_players")
    int max_players;
    @Column(name = "min_playtime")
    int min_playtime;
    @Column(name = "max_playtime")
    int max_playtime;
    @Column(name = "min_age")
    int min_age;
    @Column(name = "description_preview", length = 2500)
    String description_preview;
    @Column(name = "thumb_url")
    String thumb_url;
    @Column(name = "image_url")
    String image_url;
    @Column(name = "price")
    float price;
    @Column(name = "discount")
    float discount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="dependency_game_mech",
            joinColumns=@JoinColumn (name="idGame"),
            inverseJoinColumns=@JoinColumn(name="idMechanic"))
    private List<Mechanic> mechanicsTable;

    public List<Mechanic> getMechanics() {
        return mechanicsTable;
    }

    public void setMechanics(List<Mechanic> mechanicsTable) {
        this.mechanicsTable = mechanicsTable;
    }

    public Game() {
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String id) {
        this.idGame = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_published() {
        return year_published;
    }

    public void setYear_published(int year_published) {
        this.year_published = year_published;
    }

    public int getMin_players() {
        return min_players;
    }

    public void setMin_players(int min_players) {
        this.min_players = min_players;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }

    public int getMin_playtime() {
        return min_playtime;
    }

    public void setMin_playtime(int min_playtime) {
        this.min_playtime = min_playtime;
    }

    public int getMax_playtime() {
        return max_playtime;
    }

    public void setMax_playtime(int max_playtime) {
        this.max_playtime = max_playtime;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

    public String getPreviewDescription() {
        return description_preview;
    }

    public void setPreviewDescription(String previewDescription) {
        this.description_preview = previewDescription;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

}
