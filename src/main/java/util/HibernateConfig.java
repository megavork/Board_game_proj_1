package util;

import entity.Category;
import entity.Game;
import entity.GameMechanicDependency;
import entity.Mechanic;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateConfig {

    public HibernateConfig() {

    }

    /**
     * Return new session to work by using hibernate
     * @return org.hibernate.Session
     */
    public static SessionFactory getSessionFactory() {
        SessionFactory   sessionFactory;
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Mechanic.class);
            configuration.addAnnotatedClass(GameMechanicDependency.class);
            configuration.addAnnotatedClass(Category.class);
            configuration.addAnnotatedClass(Game.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            System.err.println("Failed to create sessionFactory object." + e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }
}
