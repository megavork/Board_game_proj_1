package services;

import dao.CategoryDao;
import entity.Category;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CategoriesService {
    private CategoryDao categoryDao = new CategoryDao();

    /**
     * Return one Category from base
     * @param idCategory
     * @return
     */
    public Category findCategoryById(String idCategory) {
        return categoryDao.findById(idCategory);
    }

    /**
     * Save Object in category base
     * @param category
     */
    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    /**
     * Update one Category in base
     * @param category
     */
    public void updateCategory(Category category) {
        categoryDao.update(category);
    }

    /**
     * Delete one Category from base
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    /**
     * Return all Categories from base
     * @return
     */
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     * Method will upload data from API and put it in base.
     * Won't work if base already has all data.
     * @return
     */
    public boolean uploadFromAPI() {
        return categoryDao.uploadFromAPI();
    }
}
