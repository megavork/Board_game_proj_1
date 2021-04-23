package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.GameDao;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import com.example.Board_game_proj_1.services.interfaces.MechanicService;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    private final String OBJECT_NAME = "games";
    private final String CATEGORY_ID = "CATEGORY_ID";
    private final String GAME_URL = "https://api.boardgameatlas.com/api/search?" +
            "categories=" + CATEGORY_ID + "&" +
            "fields=id,name,year_published,min_players,max_players,min_playtime,max_playtime,min_age,description,thumb_url,image_url,price,discount,average_user_rating,categories,mechanics&" +
            "client_id=admin";

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

    @Override
    public List findByName(String gameName) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Game where name LIKE 'gameName%' ").list();
    }



    public List<Game> findGameByCategoryId(String categoryId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Game left join Category on ").list();
    }

    /**
     * Save Object in category base
     * @param game
     */
    @Override
    public void save(Game game) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(game);
        //session.merge(game);
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


    @Override
    public boolean uploadFromAPI() {
        List<Category> categoryList = categoryService.findAll();
        for(Category category: categoryList) {
            try {
                String urlWithCategoryId = GAME_URL.replace(CATEGORY_ID, category.getIdCategories());
                JSONArray jsonArray = UploadObjectsFromAPI.getDataFromAPI(urlWithCategoryId, OBJECT_NAME);    //получили и распарсили JSON

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Game game = new Game().fromJSON(object);
                    game.setCategoryTable(getCategoriesFromArray(object.optJSONArray("categories")));
                    game.setMechanicsTable(getMechanicsFromArray(object.optJSONArray("mechanics")));

                    if(findById(game.getIdGame()) == null && !game.getDescription().isEmpty() ) {
                        save(game);
                    }
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
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
            return category;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
