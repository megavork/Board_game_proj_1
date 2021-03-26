package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.dao.interfaces.GameDao;
import com.example.Board_game_proj_1.dao.interfaces.MechanicDao;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MechanicService mechanicService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Return one Game from base
     * @param id
     * @return
     */
    @Override
    public Game findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Game.class, id);
    }

    /**
     * Save Object in category base
     * @param game
     */
    @Override
    public void save(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.save(game);
    }

    /**
     * Update one Game in base
     * @param game
     */
    @Override
    public void update(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.update(game);
    }

    /**
     * Delete one Game from base
     * @param game
     */
    @Override
    public void delete(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(game);
    }

    /**
     * Delete one Game from base by using Id
     * @param id
     */
    @Override
    public void deleteById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Game game = findById(id);
        session.delete(game);
    }

    /**
     * Return all Games from base
     * @return
     */
    @Override
    public List<Game> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Game").list();
    }

    /**
     * Method will return all mechanics which depends on idGame
     * @param idGame
     * @return
     */
    @Override
    public List<Mechanic> getGameMechanicsById(String idGame) {
        return findById(idGame).getMechanicsTable();
    }

    /**
     * Method will return all categories which depends on idGame
     * @param idGame
     * @return
     */
    @Override
    public List<Category> getGameCategoriesById(String idGame) {
        return findById(idGame).getCategoryTable();
    }

    /**
     * Method will load data from API and put it in base
     */
    public List<Game> uploadFromAPI(String URL, String handle) {

        List<Game> list = new ArrayList<>();
        JSONArray jsonArray = null;

        try {
            jsonArray = UploadObjectsFromAPI.getDateFromAPI(URL, GameDao.OBJECT_NAME); //получили и распарсили JSON
        } catch (IOException e) {
            System.out.println("JSONArray was not created!. Upload Problems.");
            e.printStackTrace();
        }

        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String idGame = jsonObject.optString("id");

            if(jsonObject.opt("description_preview").equals("") || findById(idGame) != null) {
                System.out.println("NOTHING");
                continue;
            }

            Game game = new Game();

            try {
                game.setIdGame(idGame);
                game.setName(jsonObject.optString("name"));

                    game.setYear_published(jsonObject.optInt("year_published"));
                    game.setMin_players(jsonObject.optInt("min_players"));
                    game.setMax_players(jsonObject.optInt("max_players"));
                    game.setMin_playtime(jsonObject.optInt("min_playtime"));
                    game.setMax_playtime(jsonObject.optInt("max_playtime"));
                    game.setMin_age(jsonObject.optInt("min_age"));

                game.setDescription_preview(removeBlanks(jsonObject.optString("description_preview")));

                game.setThumb_url(jsonObject.optString("thumb_url"));
                game.setImage_url(jsonObject.optString("image_url"));

                game.setPrice(jsonObject.optFloat("price"));
                game.setDiscount(jsonObject.optFloat("discount"));
                game.setAverage_user_rating(jsonObject.optFloat("average_user_rating"));

                JSONArray mechanicsArray = jsonObject.optJSONArray(MechanicDao.OBJECT_NAME);
                game.setMechanicsTable(getMechanicsFromArray(mechanicsArray));

                JSONArray categoriesArray = jsonObject.optJSONArray(CategoryDao.OBJECT_NAME);
                game.setCategoryTable(getCategoriesFromArray(categoriesArray));

                list.add(game);

            } catch (NonUniqueObjectException n) {
                continue;
            }
        }
        return list;
    }
    @Override
    public boolean uploadAllGamesFromAPI() {
        Session session = sessionFactory.getCurrentSession();
        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(GameDao.URL,GameDao.OBJECT_NAME);    //получили и распарсили JSON

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String handle = (String) object.get("handle");

                if(!handle.isEmpty()) {
                    String urlForSearchGame = GameDao.URL_WITH_HANDLE.replace("HANDLE_NAME", handle);
                    List<Game> list = uploadFromAPI(urlForSearchGame, handle);

                    for(Game game: list) {
                        System.out.println("Try to add data in DataBase");
                        session.save(game);
                        System.out.println("Data was added.");
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    /**
     * Method will remove all blanks behind and after the line
     * String.strip()
     * @param line
     * @return
     */
    private String removeBlanks(String line) {
        return line.strip();
    }

    /**
     * Method add all mechanics of game in list
     * @param mechanicsArray
     * @return
     */
    private List<Mechanic> getMechanicsFromArray(JSONArray mechanicsArray) {
        List<Mechanic> mechanics = new ArrayList<>();

        try {
            for (int i = 0; i < mechanicsArray.length(); i++) {
                JSONObject object = mechanicsArray.getJSONObject(i);
                Mechanic mechanic = mechanicService.findMechanicById(object.optString("id"));
                mechanics.add(mechanic);
            }
            System.out.println(Arrays.toString(mechanics.toArray()));
            return mechanics;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method add all categories of game in list
     * @param categoriesArray
     * @return
     */
    private List<Category> getCategoriesFromArray(JSONArray categoriesArray) {

        List<Category> category = new ArrayList<>();

        try {
            for (int i = 0; i < categoriesArray.length(); i++) {
                JSONObject object = categoriesArray.getJSONObject(i);
                Category foundCategory = categoryService.findCategoryById(object.optString("id"));
                category.add(foundCategory);
            }
            System.out.println(Arrays.toString(category.toArray()));
            return category;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
