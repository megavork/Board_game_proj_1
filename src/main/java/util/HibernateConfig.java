package util;

import entity.GameMechanics;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.json.JSONArray;
import org.json.JSONObject;


public class HibernateConfig {

    private Session session = null;

    public HibernateConfig() {

    }

    /**
     * Procedure session create
     * @return org.hibernate.Session
     */
    private Session createHibernateSession(String URL) {
        SessionFactory   sessionFactory  = null;
        ServiceRegistry  serviceRegistry = null;
        try {
            try {
                //Configuration cfg = new Configuration().configure().add("db.properties");
                //cfg.addAnnotatedClass(MechanicsAPI.class);
                //cfg = cfg.setProperty("hibernate.connection.url",URL);
                //serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
                Configuration configuration = new Configuration();
                configuration.configure();
                configuration.addAnnotatedClass(GameMechanics.class);

                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                Class.forName("com.mysql.jdbc.Driver");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                //sessionFactory = new Configuration().configure().buildSessionFactory();
                //sessionFactory = cfg.buildSessionFactory(serviceRegistry);

            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }

            session = sessionFactory.openSession();
            System.out.println("Session created");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    public void addMechanics() {
        try {
            Transaction tx;
            session = createHibernateSession(MechanicsAPI.dataBaseURL);
            //Session session = createHibernateSession(URL);              //создали сессию для hibernate
            String jsonMechanics = MechanicsAPI.getMechanicsFromAPI();  //получили json form API
            JSONArray jsonArray = JSONParser.JSONParseMechanics(jsonMechanics);

            for(int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String id = (String) object.get("id");
                String name = (String) object.get("name");
                String url = (String) object.get("url");

                GameMechanics mechanic = new GameMechanics(id, name, url);
                System.out.println("Try to add data in DataBase");
                session.save(mechanic);
                tx = session.beginTransaction();
                tx.commit();
                System.out.println("\tЗаписи добавлены");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
