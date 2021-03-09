package dao;

import entity.Game;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateConfig;
import util.UploadObjectsFromAPI;

import java.util.List;

public class GameDao {

    /**
     * URL for get data from API
     */
    final private String URL = "https://api.boardgameatlas.com/api/search?name=Catan&client_id=BE1Mg8GUFu";
    final private String objectName = "games";

    /**
     * Return one category from base
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
     * Update one category in base
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
     * Delete one category from base
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
     * Return all objects from base
     * @return
     */
    public List<Game> findAll() {
        List<Game> game = (List<Game>) HibernateConfig.getSessionFactory().openSession().createQuery("From Game").list();
        return game;
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
                game.setPreviewDescription((String) object.get("description_preview"));
                game.setThumb_url((String) object.get("thumb_url"));
                game.setImage_url((String) object.get("image_url"));
                game.setPrice( Float.parseFloat((String) object.get("price")));
                game.setDiscount(Float.parseFloat((String) object.get("discount")));

                System.out.println("Try to add data in DataBase");
                Transaction tx = session.beginTransaction();
                session.save(game);
                tx.commit();
                System.out.println("Data was added.");


            }
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
