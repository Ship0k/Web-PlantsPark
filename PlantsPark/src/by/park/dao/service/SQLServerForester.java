package by.park.dao.service;

import by.park.dao.connect.SQLServer;
import by.park.dao.ForesterDAO;
import by.park.entity.Forester;
import by.park.entity.Owner;
import static by.park.resources.SQLManager.getProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Class DAO-layer for the forester of the park.
 * The description of the methods is in the implemented interface
 */
public class SQLServerForester implements ForesterDAO {
    @Override
    public void viewTask() {
        String sql = getProperty("viewTask");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Owner owner = new Owner();
                owner.setId(resultSet.getInt("Id"));
                owner.setPlant(resultSet.getString("Plant"));
                owner.setTask(resultSet.getString("TaskType"));

                System.out.println(owner);
            }
        }catch (SQLException e) {
            e.printStackTrace();        }
    }

    @Override
    public void viewRеportList() {
        String sql = getProperty("viewRеportList");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Forester forester = new Forester();
                forester.setId(resultSet.getInt("Id"));
                forester.setPlant(resultSet.getString("Plant"));
                forester.setTask(resultSet.getString("TaskType"));

                System.out.println(forester);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void performTask(int idOwnerTask) {
        String sql = getProperty("performTask");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, idOwnerTask);
            preparedStatement.executeUpdate();
            System.out.println("Задание под ID=" + idOwnerTask + " выполнено и добавленно в лист отчет");
        } catch (SQLException e) {
            System.err.println("Задание под Id=" + idOwnerTask + " не существует");
        }
    }

    @Override
    public void deleteReport(int foresterReportId) {
        String sql = getProperty("deleteReport");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, foresterReportId);
            preparedStatement.executeUpdate();
            System.out.println("Отчет под номером Id=" + foresterReportId + " удален");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
