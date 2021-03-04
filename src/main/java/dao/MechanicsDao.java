package dao;

import entity.Mechanic;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateConfig;
import util.JSONParser;
import util.MechanicsAPI;

import java.util.List;

/*
    класс для работы с СУБД. все основные методы должны быть описаны здесь
 */
public class MechanicsDao {

    /**
     * Return one mechanic from base
     * @param id
     * @return
     */
    public Mechanic findById(int id) {
        return HibernateConfig.getSessionFactory().openSession().get(Mechanic.class, id);
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

    public void uploadFromAPI() {
        Session session = HibernateConfig.getSessionFactory().openSession();

        try {
            String jsonMechanics = MechanicsAPI.getMechanicsFromAPI();              //получили json form API
            JSONArray jsonArray = JSONParser.JSONParseMechanics(jsonMechanics);     //распарсили JSON

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                Mechanic mechanic = new Mechanic((String) object.get("id"), (String) object.get("name"));
                System.out.println("Try to add data in DataBase");

                Transaction tx = session.beginTransaction();
                session.save(mechanic);
                tx.commit();

                System.out.println("\tЗаписи добавлены");
            }

            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
