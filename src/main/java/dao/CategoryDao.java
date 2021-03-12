package dao;

import entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateConfig;
import util.UploadObjectsFromAPI;

import java.util.List;

public class CategoryDao {

    /**
     * URL for get data from API
     */
    final private String URL = "https://api.boardgameatlas.com/api/game/categories?client_id=BE1Mg8GUFu";
    final private String objectName = "categories";

    /**
     * Return one category from base
     * @param id
     * @return
     */
    public static Category findById(String id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    /**
     * Save Object in category base
     * @param category
     */
    public void save(Category category) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(category);
        tx1.commit();
        session.close();
    }

    /**
     * Update one category in base
     * @param category
     */
    public void update(Category category) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(category);
        tx1.commit();
        session.close();
    }

    /**
     * Delete one category from base
     * @param category
     */
    public void delete(Category category) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(category);
        tx1.commit();
        session.close();
    }

    /**
     * Return all objects from base
     * @return
     */
    public List<Category> findAll() {
        List<Category> categories = (List<Category>) HibernateConfig.getSessionFactory().openSession().createQuery("From Category").list();
        return categories;
    }

    /**
     * Method will load data from API and put it in base
     */
    public void uploadFromAPI() {
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(URL,objectName);    //получили и распарсили JSON

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Category category = new Category((String) object.get("id"), (String) object.get("name"));
                System.out.println("Try to add data in DataBase");

                Transaction tx = session.beginTransaction();
                session.save(category);
                tx.commit();

                System.out.println("Data was added.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
