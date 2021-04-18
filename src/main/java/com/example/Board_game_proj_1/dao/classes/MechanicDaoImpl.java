package com.example.Board_game_proj_1.dao.classes;

import com.example.Board_game_proj_1.dao.interfaces.MechanicDao;
import com.example.Board_game_proj_1.entity.Game;
import com.example.Board_game_proj_1.entity.Mechanic;
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
public class MechanicDaoImpl implements MechanicDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * URL for get data from API
     */
    final private String MECHANIC_URL = "https://api.boardgameatlas.com/api/game/mechanics?client_id=admin";
    final private String OBJECT_NAME = "mechanics";

    /**
     * Return one mechanic from base
     * @param id
     * @return
     */
    public Mechanic findById(String id) {
        Session session = sessionFactory.getCurrentSession();
        Mechanic mechanic = session.get(Mechanic.class, id);
        return mechanic;
    }

    /**
     * Save Object in mechanic base
     * @param mechanic
     */
    public void save(Mechanic mechanic) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(mechanic);
    }

    /**
     * Update one mechanic in base
     * @param mechanic
     */
    public void update(Mechanic mechanic) {
        Session session = sessionFactory.getCurrentSession();
        session.update(mechanic);
    }

    /**
     * Delete one object from base
     * @param mechanic
     */
    public void delete(Mechanic mechanic) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(mechanic);
    }

    /**
     * Return all objects from base
     * @return
     */
    public List findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("From Mechanic").list();
    }

    /**
     * Method will load data from API and put it in base
     */
    public boolean uploadFromAPI() {
        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDataFromAPI(MECHANIC_URL,OBJECT_NAME);

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Mechanic mechanic = new Mechanic(object.optString("id"), object.optString("name"));
                save(mechanic);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private List<Game> getGameByMechanic(String mechanicId) throws IOException {
        Session session = sessionFactory.getCurrentSession();

        List<Game> gameList = new ArrayList<>();
        String mechanicUrl = MechanicDao.GET_GAME_URL.replace("MECHANIC_ID", mechanicId);
        JSONArray jsonArray = UploadObjectsFromAPI.getDataFromAPI(mechanicUrl, OBJECT_NAME);

        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            Game game = session.get(Game.class, object.optString("id"));
            gameList.add(game);
        }
        return gameList;
    }
}
