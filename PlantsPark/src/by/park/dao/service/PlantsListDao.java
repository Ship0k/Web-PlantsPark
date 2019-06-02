package by.park.dao.service;

import by.park.dao.connect.SQLServer;
import by.park.entity.object.Plant;
import by.park.entity.object.PlantDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static by.park.resources.SQLManager.getProperty;

public class PlantsListDao {
    private static PlantsListDao ourInstance = new PlantsListDao();

    public static PlantsListDao getInstance() {
        return ourInstance;
    }

    private ArrayList<Plant> plants = new ArrayList<>();
    private int curPlant = -1;
    private int idInsert;                       //for method insert()

    private PlantsListDao() {
        String sql = getProperty("viewAllPlants");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Plant plant = new Plant();
                PlantDetails pd = new PlantDetails();

                plant.setId(resultSet.getInt("Id"));
                plant.setTitle(resultSet.getString("Title"));
                pd.setLandingData(resultSet.getDate("LandingDate"));
                pd.setArtWorkN(resultSet.getInt("ArtWorkN"));
                pd.setTreatmentN(resultSet.getInt("TreatmentN"));
                pd.setDestructionDate(resultSet.getDate("DestructionDate"));
                plant.setPlantDetails(pd);

                addPlant(plant);
            }
            curPlant = 0;
            idInsert = plants.size();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    public Plant getCurPlant() {
        if (curPlant > -1) {
            return plants.get(curPlant);
        } else {
            return null;
        }
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPrev() {
        if (curPlant > 0) {
            curPlant--;
        }
        return getCurPlant();
    }

    public Plant getNext() {
        if (curPlant < plants.size() - 1) {
            curPlant++;
        }
        return getCurPlant();
    }

    public Plant byId(int id) {
        int i = -1;
        for (Plant p : plants) {
            ++i;
            if (p.getId() == id) {
                curPlant = i;
                return getCurPlant();
            }
        }
        return getCurPlant();
    }

    public Plant insert(String title){
        String addSql = getProperty("addSql");
        String extractSql = getProperty("extractSql");
        int id = -1;

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(addSql);
             PreparedStatement query = connection.prepareStatement(extractSql))
        {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();

            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("Id");
            }
            PlantDetails pd = new PlantDetails();
            pd.setArtWorkN(0);
            pd.setTreatmentN(0);

            Plant plant = new Plant();
            plant.setId(id);
            plant.setTitle(title);
            plant.setPlantDetails(pd);

            plants.add(plant);
            curPlant = plants.size() - 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getCurPlant();
    }

    public Plant update(String name){
        String newName = getProperty("newName");
        Plant plant = plants.get(curPlant);
        plant.setTitle(name);
        try (Connection connection = SQLServer.createConnection();
            PreparedStatement n = connection.prepareStatement(newName))
        {
            n.setString(1, name);
            n.setInt(2, plant.getId());
            n.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    public Plant delete(){
        Plant plant = plants.get(curPlant);

        String sql = getProperty("deletePlant");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, plant.getId());
            preparedStatement.executeUpdate();
            plants.remove(curPlant);
            curPlant = 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants.get(0);
    }

    public Plant landing(){
        Plant plant = plants.get(curPlant);
        PlantDetails pd = plant.getPlantDetails();
        if(pd.getLandingData() == null) {
            pd.setLandingData(new Date());
            plant.setPlantDetails(pd);
            String  landingSql = getProperty("landingSql");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement landing = connection.prepareStatement(landingSql))
            {
                landing.setInt(1, plant.getId());
                landing.executeUpdate();
                return plant;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getCurPlant();
    }

    public Plant art(){
        Plant plant = plants.get(curPlant);
        PlantDetails pd = plant.getPlantDetails();
        if(pd.getDestructionDate() == null && pd.getLandingData() != null) {
            pd.setArtWorkN(pd.getArtWorkN() + 1);
            plant.setPlantDetails(pd);
            String  artSql = getProperty("artSql");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement art = connection.prepareStatement(artSql))
            {
                art.setInt(1, plant.getId());
                art.executeUpdate();
            return plant;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getCurPlant();
    }

    public Plant treatment(){
        Plant plant = plants.get(curPlant);
        PlantDetails pd = plant.getPlantDetails();
        if(pd.getDestructionDate() == null && pd.getLandingData() != null) {
            pd.setTreatmentN(pd.getTreatmentN() + 1);
            plant.setPlantDetails(pd);
            String  treatmentSql = getProperty("treatmentSql");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement art = connection.prepareStatement(treatmentSql))
            {
                art.setInt(1, plant.getId());
                art.executeUpdate();
                return plant;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getCurPlant();
    }

    public Plant destruction(){
        Plant plant = plants.get(curPlant);
        PlantDetails pd = plant.getPlantDetails();
        if(pd.getDestructionDate() == null && pd.getLandingData() != null) {
            pd.setDestructionDate(new Date());
            plant.setPlantDetails(pd);
            String  deleteSql = getProperty("deleteSql");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement art = connection.prepareStatement(deleteSql))
            {
                art.setInt(1, plant.getId());
                art.executeUpdate();
                return plant;
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return getCurPlant();
    }
}
