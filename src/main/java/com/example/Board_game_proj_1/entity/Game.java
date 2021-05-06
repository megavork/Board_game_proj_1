package com.example.Board_game_proj_1.entity;

import com.example.Board_game_proj_1.dto.GameDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "board_game_sch", name = "game_objects" )
public class Game implements Serializable {
    @Id
    @Column(name = "idGame", length = 15)
    private String idGame;
    @Column(name = "name")
    private String name;
    @Column(name = "year_published")
    private int year_published;
    @Column(name = "min_players")
    private int min_players;
    @Column(name = "max_players")
    private int max_players;
    @Column(name = "min_playtime")
    private int min_playtime;
    @Column(name = "max_playtime")
    private int max_playtime;
    @Column(name = "min_age")
    private int min_age;
    @Column(name = "description", length = 8000)
    private String description;
    @Column(name = "thumb_url")
    private String thumb_url;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "price")
    //UPDATE game_objects SET price = (FLOOR((RAND() * (200-100+1))+100)) WHERE price = 0;
    //select * from game_objects;
    private float price;
    @Column(name = "discount")
    private float discount;
    @Column(name = "average_user_rating")
    private float average_user_rating;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "gameList")
    List<Mechanic> mechanicsTable = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "gameList")
    List<Category> categoryTable = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "games")
    List<Order> ordersList = new ArrayList<>();

    public GameDto toGameDto() {
        GameDto gameDto = new GameDto();
        gameDto.setIdGame(this.getIdGame());
        gameDto.setName(this.getName());
        gameDto.setYear_published(this.getYear_published());
        gameDto.setMin_players(this.getMin_players());
        gameDto.setMax_players(this.getMax_players());
        gameDto.setMin_playtime(this.getMin_playtime());
        gameDto.setMax_playtime(this.getMax_playtime());
        gameDto.setMin_age(this.getMin_age());
        gameDto.setDescription(this.getDescription());
        gameDto.setThumb_url(this.getThumb_url());
        gameDto.setImage_url(this.getImage_url());
        gameDto.setPrice(this.getPrice());
        gameDto.setDiscount(this.getDiscount());
        gameDto.setAverage_user_rating(this.getAverage_user_rating());
        return gameDto;
    }

    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getAverage_user_rating() {
        return average_user_rating;
    }

    public void setAverage_user_rating(float average_user_rating) {
        this.average_user_rating = average_user_rating;
    }

    public List<Mechanic> getMechanicsTable() {
        return mechanicsTable;
    }

    public void setMechanicsTable(List<Mechanic> mechanicsTable) {
        this.mechanicsTable = mechanicsTable;
    }

    public List<Category> getCategoryTable() {
        return categoryTable;
    }

    public void setCategoryTable(List<Category> categoryTable) {
        this.categoryTable = categoryTable;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Game fromJSON(JSONObject jsonObject) {

        this.description = Jsoup.parse(jsonObject.optString("description").strip()).text();
        this.idGame = jsonObject.optString("id");
        this.name = jsonObject.optString("name");
        this.year_published = jsonObject.optInt("year_published");
        this.min_players = jsonObject.optInt("min_players");
        this.max_players = jsonObject.optInt("max_players");
        this.min_playtime = jsonObject.optInt("min_playtime");
        this.max_playtime = jsonObject.optInt("max_playtime");
        this.min_age = jsonObject.optInt("min_age");
        this.thumb_url = jsonObject.optString("thumb_url");
        this.image_url = jsonObject.optString("image_url");
        this.price = jsonObject.optFloat("price");
        this.discount = jsonObject.optFloat("discount");
        this.average_user_rating = jsonObject.optFloat("average_user_rating");

        return this;
    }
}
