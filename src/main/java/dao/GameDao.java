package dao;

import entity.Category;
import entity.Game;
import entity.Mechanic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateConfig;
import util.UploadObjectsFromAPI;

import java.util.ArrayList;
import java.util.List;

public class GameDao {

    /**
     * URL for get data from API
     */
    final private String URL = "https://api.boardgameatlas.com/api/search?client_id=BE1Mg8GUFu";
    final private String objectName = "games";

    /**
     * Return one Game from base
     * @param id
     * @return
     */
    public Game findById(String id) {
        return HibernateConfig.getSessionFactory().openSession().get(Game.class, id);
    }

    /**
     * Save Object in category base
     * @param game
     */
    public void save(Game game) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(game);
        tx1.commit();
        session.close();
    }

    /**
     * Update one Game in base
     * @param game
     */
    public void update(Game game) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(game);
        tx1.commit();
        session.close();
    }

    /**
     * Delete one Game from base
     * @param game
     */
    public void delete(Game game) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(game);
        tx1.commit();
        session.close();
    }

    /**
     * Delete one Game from base by using Id
     * @param id
     */
    public void deleteById(String id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Game game = session.get(Game.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(game);
        tx1.commit();
        session.close();
    }

    /**
     * Return all Games from base
     * @return
     */
    public List<Game> findAll() {
        List<Game> game = (List<Game>) HibernateConfig.getSessionFactory().openSession().createQuery("From Game").list();
        return game;
    }

    /**
     * Method will return all mechanics which depends on idGame
     * @param idGame
     * @return
     */
    public List<Mechanic> getGameMechanicsById(String idGame) {
        Game game = findById(idGame);
        return game.getMechanicsTable();
    }

    /**
     * Method will return all categories which depends on idGame
     * @param idGame
     * @return
     */
    public List<Category> getGameCategoriesById(String idGame) {
        Game game = findById(idGame);
        return game.getCategoryTable();
    }

    /**
     * Method will load data from API and put it in base
     */
    public boolean uploadFromAPI() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        try {

            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(URL,objectName);    //получили и распарсили JSON

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String idGame = (String) object.get("id");

                Game game = new Game();
                game.setIdGame(idGame);
                game.setName((String) object.get("name"));
                game.setYear_published((int) object.get("year_published"));
                game.setMin_players((int) object.get("min_players"));
                game.setMax_players((int) object.get("max_players"));
                game.setMin_playtime((int) object.get("min_playtime"));
                game.setMax_playtime((int) object.get("max_playtime"));
                game.setMin_age((int) object.get("min_age"));

                String description_preview = (String) object.get("description_preview");
                game.setDescription_preview(removeBlanks(description_preview));

                game.setThumb_url((String) object.get("thumb_url"));
                game.setImage_url((String) object.get("image_url"));
                game.setPrice( Float.parseFloat((String) object.get("price")));
                game.setDiscount(Float.parseFloat((String) object.get("discount")));

                JSONArray mechanicsArray = (JSONArray) object.get("mechanics");
                game.setMechanicsTable(getMechanicsFromApi(mechanicsArray));

                JSONArray categoriesArray = (JSONArray) object.get("categories");
                game.setCategoryTable(getCategoriesFromApi(categoriesArray));

                System.out.println("Try to add data in DataBase");
                Transaction tx = session.beginTransaction();
                session.save(game);
                tx.commit();
                System.out.println("Data was added.");
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            session.close();
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
    private List<Mechanic> getMechanicsFromApi(JSONArray mechanicsArray) {
        List<Mechanic> mechanics = new ArrayList<>();
        try {
            for (int i = 0; i < mechanicsArray.length(); i++) {
                JSONObject object = mechanicsArray.getJSONObject(i);
                String id = (String) object.get("id");
                mechanics.add(MechanicsDao.findById(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return mechanics;
    }

    /**
     * Method add all categories of game in list
     * @param categoriesArray
     * @return
     */
    private List<Category> getCategoriesFromApi(JSONArray categoriesArray) {
        List<Category> category = new ArrayList<>();
        try {
            for (int i = 0; i < categoriesArray.length(); i++) {
                JSONObject object = categoriesArray.getJSONObject(i);
                String id = (String) object.get("id");
                category.add(CategoryDao.findById(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return category;
    }
}
