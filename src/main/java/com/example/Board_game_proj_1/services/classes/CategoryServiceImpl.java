package com.example.Board_game_proj_1.services.classes;

import com.example.Board_game_proj_1.dao.interfaces.CategoryDao;
import com.example.Board_game_proj_1.dto.CategoryDto;
import com.example.Board_game_proj_1.entity.Category;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.services.interfaces.CategoryService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@NoArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    /**
     * Return one Category from base
     * @param idCategory
     * @return
     */
    @Override
    @Transactional
    public Category findCategoryById(String idCategory) {
        return categoryDao.findById(idCategory);
    }

    @Override
    @Transactional
    public List<CategoryDto> findByListId(List listId, int game_count) {
        return categoryDao.findByListId(listId,game_count);
    }

    /**
     * Save Object in category base
     * @param category
     */
    @Override
    @Transactional
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    /**
     * Update one Category in base
     * @param category
     */
    @Override
    @Transactional
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    /**
     * Delete one Category from base
     * @param category
     */
    @Override
    @Transactional
    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    /**
     * Return all Categories from base
     * @return
     */
    @Override
    @Transactional
    public List<Category> findAllWithGames() {
        return categoryDao.findAll();
    }

    /**
     * Return all Categories from base
     * @return
     */
    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    @Override
    @Transactional
    public boolean uploadFromAPI() {
        return categoryDao.uploadFromAPI();
    }

    @Override
    @Transactional
    public List<CategoryDto> getCountOfGameFromEachCategory (int category_count, int game_count, int page_number) {

        return categoryDao.getCountOfGameFromEachCategory(category_count,game_count, page_number);
    }
}
