package by.park.dao.service;

import by.park.dao.PlantsInfoDAO;
import by.park.dao.connect.SQLServer;
import by.park.entity.object.Plant;
import by.park.entity.object.PlantDetails;
import static by.park.resources.SQLManager.getProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Class DAO-layer is associated with the plants of the park.
 * The description of the methods is in the implemented interface
 */
public class SQLServerPlantsInfo implements PlantsInfoDAO {
    @Override
    public void viewAllPlants() {
        String sql = getProperty("viewAllPlants");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Plant plant = new Plant();
                PlantDetails info = new PlantDetails();

                plant.setTitle(resultSet.getString("Title"));
                plant.setId(resultSet.getInt("Id"));
                info.setLandingData(resultSet.getDate("LandingDate"));
                info.setArtWorkN(resultSet.getInt("ArtWorkN"));
                info.setTreatmentN(resultSet.getInt("TreatmentN"));
                info.setDestructionDate(resultSet.getDate("DestructionDate"));

                System.out.print(plant);
                System.out.println(info);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plant viewPlantById(int idPlant) {
        Plant plant = new Plant();
        String sql = getProperty("viewPlantById");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setLong(1, idPlant);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PlantDetails pd = new PlantDetails();
                plant.setId(resultSet.getInt("Id"));
                plant.setTitle(resultSet.getString("Title"));
                pd.setLandingData(resultSet.getDate("LandingDate"));
                pd.setArtWorkN(resultSet.getInt("ArtWorkN"));
                pd.setTreatmentN(resultSet.getInt("TreatmentN"));
                pd.setDestructionDate(resultSet.getDate("DestructionDate"));
                plant.setPlantDetails(pd);

                System.out.print(plant);
                System.out.println(plant.getPlantDetails());
            }else {
                System.err.println("Растения под Id=" + idPlant + " нет в базе");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    @Override
    public void viewDestroyedPlants() {
        String sql = getProperty("viewDestroyedPlants");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Plant plant = new Plant();
                PlantDetails info = new PlantDetails();

                plant.setId(resultSet.getInt("Id"));
                plant.setTitle(resultSet.getString("Title"));
                info.setLandingData(resultSet.getDate("LandingDate"));
                info.setTreatmentN(resultSet.getInt("TreatmentN"));
                info.setArtWorkN(resultSet.getInt("ArtWorkN"));
                info.setDestructionDate(resultSet.getDate("DestructionDate"));

                System.out.print(plant);
                System.out.println(info);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void quantityPlantsByName(String title) {
        String sql = getProperty("quantityPlantsByName");
        int quantity;

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
             preparedStatement.setString(1, title);
             ResultSet resultSet = preparedStatement.executeQuery();

             if (resultSet.next()) {
                 quantity = resultSet.getInt("Quantity");
                 System.out.println("Растений вида: " + title + " " + quantity + " ед.");
             }else {
                 System.err.println("Растения '" + title + "' нет в базе");
             }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void quantityArtProcessed() {
        String sql = getProperty("quantityArtProcessed");
        int quantity;

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            quantity = resultSet.getInt("Quantity");
            System.out.println("Художественная обработка растений была " +
                    "проведена " + quantity + " раз");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void quantityСured() {
        String sql = getProperty("quantityСured");
        int quantity;

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            quantity = resultSet.getInt("Quantity");
            System.out.println("Лечение растений было проведено " + quantity + " раз");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
