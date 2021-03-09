package dao;

import entity.GameMechanicDependency;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HibernateConfig;
import util.UploadObjectsFromAPI;


public class DependMechDao {

    final private String URL = "https://api.boardgameatlas.com/api/search?name=Catan&client_id=BE1Mg8GUFu";
    final private String objectName = "games";

    /**
     * Method will load data from API and put it in base
     */
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
                JSONArray mechanicsArray = (JSONArray) object.get("mechanics");

                for(int j=0; j<mechanicsArray.length(); j++) {
                    JSONObject mechanicObject = mechanicsArray.getJSONObject(i);
                    String idMechanic = (String) mechanicObject.get("id");
                    GameMechanicDependency depend = new GameMechanicDependency(idGame,idMechanic);

                    System.out.println("Try to add data in DataBase");
                    Transaction tx = session.beginTransaction();
                    session.save(depend);
                    tx.commit();
                    System.out.println("Data was added.");
                }
            }
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
