package com.example.Board_game_proj_1.dto;

import com.example.Board_game_proj_1.entity.Game;
import lombok.Data;

@Data
public class GameDto {

    String idGame;
    String name;
    int year_published;
    int min_players;
    int max_players;
    int min_playtime;
    int max_playtime;
    int min_age;
    String description_preview;
    String thumb_url;
    String image_url;
    float price;
    float discount;
    float average_user_rating;

    public Game fromGameDtoToGame() {
        Game game = new Game();
        game.setIdGame(this.getIdGame());
        game.setName(this.getName());
        game.setYear_published(this.getYear_published());
        game.setMin_players(this.getMin_players());
        game.setMax_players(this.getMax_players());
        game.setMin_playtime(this.getMin_playtime());
        game.setMax_playtime(this.getMax_playtime());
        game.setMin_age(this.getMin_age());
        game.setDescription(this.getDescription_preview());
        game.setThumb_url(this.getThumb_url());
        game.setImage_url(this.getImage_url());
        game.setPrice(this.getPrice());
        game.setDiscount(this.getDiscount());
        game.setAverage_user_rating(this.getAverage_user_rating());

        return game;
    }

}
