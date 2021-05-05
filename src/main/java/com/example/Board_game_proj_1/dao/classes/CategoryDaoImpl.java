package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.services.interfaces.GameService;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final String CATEGORY_URL = "https://api.boardgameatlas.com/api/game/categories?client_id=admin";
    private final String OBJECT_NAME = "categories";

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
    public List<CategoryDto> findByListId(List<String> listId, int game_count) {
        List<CategoryDto> resultList = new ArrayList<>();

        for(String id: listId) {
            CategoryDto categoryDto = new CategoryDto();
            Category category = findById(id);

            if(!category.getGameList().isEmpty())
                categoryDto.setGameList(category.getGameList());

            categoryDto.setIdCategories(category.getIdCategories());
            categoryDto.setName(category.getName());

            resultList.add(categoryDto);
        }
        return resultList;
    }

    /**
     * Return all objects from base
     * @return
     */
    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createQuery("From Category").getResultList();
        return categoryList;
    }

    /**
     * Return all objects limit base
     * @return
     */
    @Override
    public List<Category> findAllWithGames(int limit, int offset) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Category").setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Override
    public List<CategoryDto> getCountOfGameFromEachCategory(int category_count, int game_count, int page_number) {
        final int first_index = 0;
        List<Category> categoryList = findAllWithGames(category_count, page_number*category_count);
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(Category category: categoryList) {
            CategoryDto categoryDto = category.toCategoryDto();

            if(game_count > category.getGameList().size()) {
                categoryDto.setGameList(category.getGameList().subList(first_index,category.getGameList().size()));
            } else {
                categoryDto.setGameList(category.getGameList().subList(first_index,game_count));
            }
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
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
