package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final String SQL_FIND_5_GAME_FROM_EACH_CATEGORY = "SELECT COUNT(:counters) From Category";
    private static final int COUNT_OF_ONE_REQUEST = 5;

    Comparator<Game> compare = Comparator.comparing(Game::getAverage_user_rating).reversed();
    //SELECT  code,
    //LAG(code) OVER(ORDER BY code) prev_code,
    //LEAD(code) OVER(ORDER BY code) next_code
    //FROM printer;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private GameService gameService;

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

    @Override
    public List<Category> getCountOfGameFromEachCategory(String count) {
        List<Category> categoryList = findAll();
        return categoryList.stream().peek(category -> category.getGameList().stream().sorted(compare).limit(COUNT_OF_ONE_REQUEST)).peek(category -> category.toJson()).collect(Collectors.toList());

        //return categoryList;
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
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Category").list();
    }

    /**
     * Method will load data from API and put it in base
     */
    @Override
    public boolean uploadFromAPI() {
        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(CategoryDao.URL,CategoryDao.OBJECT_NAME);    //получили и распарсили JSON

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Category category = new Category(object.optString("id"), object.optString("name"));
                save(category);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setGameListForEachCategory() {
        int count = 0;
        /*
        нужно пройтись по списку категорий и получать JSON с играми для каждой категории.
        пройдемся по каждой игре, найдем ее в нашей базе и только тогда запишем в список игр для категории.
        повторим.
         */
        //у этого дерьма не все категории!!!

        final String URL = CategoryDao.URL;
        List<Category> categoryList = findAll();

        for(Category category: categoryList) {
            String URL_WITH_CATEGORY_ID = URL.replace("CATEGORY_ID",category.getIdCategories());
            try {
                count++;
                JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(URL_WITH_CATEGORY_ID,GameDaoImpl.OBJECT_NAME);
                List<Game> gameList = createGameListFromAPI(jsonArray);
                category.setGameList(gameList);
                update(category);
                //Thread.sleep(200);
            } catch (IOException e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
        return true;
    }

    private List<Game> createGameListFromAPI(JSONArray array) {
        List<Game> gameList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            String gameId = object.optString("id");
            Game game = gameService.findById(gameId);
            if(game != null) {
                gameList.add(game);
            }
        }
        return gameList;
    }

}
