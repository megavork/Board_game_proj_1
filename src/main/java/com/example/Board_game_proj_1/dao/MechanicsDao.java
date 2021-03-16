package com.example.Board_game_proj_1.dao;

import com.example.Board_game_proj_1.entity.Mechanic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import com.example.Board_game_proj_1.util.HibernateConfig;
import com.example.Board_game_proj_1.util.UploadObjectsFromAPI;

import java.util.List;

/*
    класс для работы с СУБД. все основные методы должны быть описаны здесь
 */
public class MechanicsDao {
    /**
     * URL for get data from API
     */
    final private String URL = "https://api.boardgameatlas.com/api/game/mechanics?client_id=BE1Mg8GUFu";
    final private String objectName = "mechanics";

    /**
     * Return one mechanic from base
     * @param id
     * @return
     */
    public static Mechanic findById(String id) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Mechanic mechanic = session.get(Mechanic.class, id);
        session.close();
        return mechanic;
    }

    /**
     * Save Object in mechanic base
     * @param mechanic
     */
    public void save(Mechanic mechanic) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(mechanic);
        tx1.commit();
        session.close();
    }

    /**
     * Update one mechanic in base
     * @param mechanic
     */
    public void update(Mechanic mechanic) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(mechanic);
        tx1.commit();
        session.close();
    }

    /**
     * Delete one object from base
     * @param mechanic
     */
    public void delete(Mechanic mechanic) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(mechanic);
        tx1.commit();
        session.close();
    }

    /**
     * Return all objects from base
     * @return
     */
    public List<Mechanic> findAll() {
        List<Mechanic> mechanics = (List<Mechanic>) HibernateConfig.getSessionFactory().openSession().createQuery("From Mechanic").list();
        return mechanics;
    }

    /**
     * Method will load data from API and put it in base
     */
    public boolean uploadFromAPI() {
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            JSONArray jsonArray = UploadObjectsFromAPI.getDateFromAPI(URL,objectName);

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Mechanic mechanic = new Mechanic((String) object.get("id"), (String) object.get("name"));
                System.out.println("Try to add data in DataBase");

                Transaction tx = session.beginTransaction();
                session.save(mechanic);
                tx.commit();

                System.out.println("Записи добавлены");
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
}
