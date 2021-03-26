package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.dao.interfaces.GameDao;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Return one category from base
     * @param id
     * @return
     */
    @Override
    public Category findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class, id);
        return category;
    }

    /**
     * Save Object in category base
     * @param category
     */
    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.save(category);
    }

    /**
     * Update one category in base
     * @param category
     */
    @Override
    public void update(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.update(category);
    }

    /**
     * Delete one category from base
     * @param category
     */
    @Override
    public void delete(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(category);
    }

    /**
     * Return all objects from base
     * @return
     */
    @Override
    public List findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Category").list();
    }

    /**
     * Method will load data from API and put it in base
     */
    @Override
    public boolean uploadFromAPI() {
        Session session = sessionFactory.getCurrentSession();

        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(CategoryDao.URL,CategoryDao.OBJECT_NAME);    //получили и распарсили JSON

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Category category = new Category((String) object.opt("id"), (String) object.opt("name"));
                category.setGameList(getGameByCategory(category.getIdCategories()));

                System.out.println("Try to add data in DataBase");
                session.save(category);
                System.out.println("Data was added.");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private List<Game> getGameByCategory(String categoryId) throws IOException {
        Session session = sessionFactory.getCurrentSession();

        List<Game> gameList = new ArrayList<>();
        String categoryUrl = CategoryDao.GET_GAME_URL.replace("CATEGORY_ID", categoryId);
        JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(categoryUrl, GameDao.OBJECT_NAME);

        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            Game game = session.get(Game.class, object.optString("id"));        //?????????????????????????
            gameList.add(game);
        }
        return gameList;
    }
}
