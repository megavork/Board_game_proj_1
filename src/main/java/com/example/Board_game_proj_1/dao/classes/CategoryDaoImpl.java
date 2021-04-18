package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final String CATEGORY_URL = "https://api.boardgameatlas.com/api/game/categories?client_id=admin";
    private final String OBJECT_NAME = "categories";

    private final int COUNT_GAMES_IN_ONE_REQUEST = 5;

    Comparator<Game> compare = Comparator.comparing(Game::getAverage_user_rating).reversed();

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
    public List<Category> findFirstCount(int count) {
        if(count <= 0) {
            return new ArrayList<>();
        }
        String limitQuery = "FROM Category LIMIT "+ count + " OFFSET " + (count-COUNT_GAMES_IN_ONE_REQUEST);
        Session session = sessionFactory.getCurrentSession();
        Query query =  session.createQuery(limitQuery);
        return (List<Category>) query.getSingleResult();
    }

    @Override
    public List<CategoryDto> getCountOfGameFromEachCategory(String category_count, String game_count) {
        final int CATEGORY_COUNT = Integer.parseInt(category_count);
        final int GAME_COUNT = Integer.parseInt(game_count);

        List<Category> categoryList = findFirstCount(CATEGORY_COUNT);

        for(Category category: categoryList) {
            List<Game> gameList = category.getGameList().stream().sorted(compare).collect(Collectors.toList());
            category.setGameList(gameList);
        }

        List<CategoryDto> result = new ArrayList<>();

        for(Category category: categoryList) {
            int lenght = category.getGameList().size();

            CategoryDto categoryDto = category.toCategoryDto();
            categoryDto.setGameList(new ArrayList<>());

            for(int i = GAME_COUNT; i < lenght || i < GAME_COUNT + COUNT_GAMES_IN_ONE_REQUEST +1; i++) {
                categoryDto.getGameList().add(category.getGameList().get(i));
            }
            result.add(categoryDto);
        }
        return result;
    }

    /**
     * Save Object in category base
     * @param category
     */
    @Override
    public void save(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
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
            JSONArray jsonArray = UploadObjectsFromAPI.getDataFromAPI(CATEGORY_URL,OBJECT_NAME);    //получили и распарсили JSON

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
}
