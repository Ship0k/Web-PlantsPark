package by.park.dao.service;

import by.park.dao.connect.SQLServer;
import by.park.dao.OwnerDAO;
import by.park.entity.Forester;
import by.park.entity.object.Plant;
import static by.park.resources.SQLManager.getProperty;

import java.sql.*;

/*
 * Class DAO-layer for the owner of the park.
 * The description of the methods is in the implemented interface
 */
public class SQLServerOwner implements OwnerDAO {
    @Override
    public int insertPlant(String title) {
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
            System.out.println("Растение " + title + " добавлено в базу с присвоением Id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Plant findPlant(int idPlant) {
        String sql = getProperty("findPlant");
        Plant plant = null;

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, idPlant);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                plant = new Plant();
                plant.setId(resultSet.getInt("Id"));
                plant.setTitle(resultSet.getString("Title"));

            }else {
                System.err.println("Растения с Id=" + idPlant + " нет в базе");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return plant;
    }

    @Override
    public void deletePlant(int idPlant) {
        String sql = getProperty("deletePlant");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, idPlant);
            preparedStatement.executeUpdate();
            System.out.println("Растение с Id=" + idPlant + " удалено");
        } catch (SQLException e) {
            System.err.println("Растение под Id=" + idPlant + " не может быть удалено! " +
                    "На данный момент с ним проводятся работы в парке");
        }
    }

    @Override
    public void taskLanding(Plant plant) {
        if (plant == null) {
            System.err.println("Объект типа Plant не имеет реализации переменных");
        }else {
            String sql = getProperty("taskLanding");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql))
            {
                preparedStatement.setInt(1, plant.getId());
                preparedStatement.executeUpdate();
                System.out.println("Заданее: Высадить " + plant + " - добавлено в лист заданий");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void taskArtProcessing(Plant plant) {
        if (plant == null) {
            System.err.println("Объект типа Plant не имеет реализации переменных");
        }else {
            String sql = getProperty("taskArtProcessing");

            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql))
            {
                preparedStatement.setInt(1, plant.getId());
                preparedStatement.executeUpdate();
                System.out.println("Заданее: Художественно обработать " + plant + " - добавлено в лист заданий");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void taskTreatment(Plant plant) {
        if (plant == null) {
            System.err.println("Объект типа Plant не имеет реализации переменных");
        }else {
            String sql = getProperty("taskTreatment");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql))
            {
                preparedStatement.setInt(1, plant.getId());
                preparedStatement.executeUpdate();
                System.out.println("Заданее: Лечить " + plant + " - добавлено в лист заданий");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void taskDestruction(Plant plant) {
        if (plant == null) {
            System.err.println("Объект типа Plant не имеет реализации переменных");
        }else {
            String sql = getProperty("taskDestruction");
            try (Connection connection = SQLServer.createConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql))
            {
                preparedStatement.setInt(1, plant.getId());
                preparedStatement.executeUpdate();
                System.out.println("Заданее: Уничтожить " + plant + " - добавлено в лист заданий");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteTask(int idTask) {
        String sql = getProperty("deleteTask");
        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, idTask);
            preparedStatement.executeUpdate();
            System.out.println("Задание под номером Id=" + idTask + " удалено");
        } catch (SQLException e) {
            System.err.println("Задания под номером Id=" + idTask + " не может быть удалено," +
                    "т.к. уже выполненно и еще не подтверждено в базе данных");
        }
    }

    @Override
    public void viewForesterReport() {
        String sql = getProperty("viewForesterReport");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery())
        {
            while (resultSet.next()) {
                Forester forester = new Forester();
                forester.setId(resultSet.getInt("Id"));
                forester.setTask(resultSet.getString("TaskType"));
                forester.setPlant(resultSet.getString("Plant"));

                System.out.println(forester);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmReport(int idReportForester) {
        String extrSql = getProperty("extrSql");
        String  landingSql = getProperty("landingSql");
        String  artSql = getProperty("artSql");
        String  treatmentSql = getProperty("treatmentSql");
        String  deleteSql = getProperty("deleteSql");
        String cleaningF = getProperty("cleaningF");
        String cleaningO = getProperty("cleaningO");

        try (Connection connection = SQLServer.createConnection();
             PreparedStatement extract = connection.prepareStatement(extrSql))
        {
            extract.setInt(1, idReportForester);
            ResultSet resultSet = extract.executeQuery();

            if (resultSet.next()) {
                int ownerId = resultSet.getInt("Id");
                int plantId = resultSet.getInt("PlantId");
                int taskId = resultSet.getInt("TaskId");

                if (taskId == 1) {
                    PreparedStatement landing = connection.prepareStatement(landingSql);
                    landing.setInt(1, plantId);
                    landing.executeUpdate();
                    landing.close();
                } else if (taskId == 2) {
                    PreparedStatement art = connection.prepareStatement(artSql);
                    art.setInt(1, plantId);
                    art.executeUpdate();
                    art.close();
                } else if (taskId == 3) {
                    PreparedStatement treatment = connection.prepareStatement(treatmentSql);
                    treatment.setInt(1, plantId);
                    treatment.executeUpdate();
                    treatment.close();
                } else if (taskId == 4) {
                    PreparedStatement delete = connection.prepareStatement(deleteSql);
                    delete.setInt(1, plantId);
                    delete.executeUpdate();
                    delete.close();
                }
                System.out.println("Выполненное задание по отчену Id=" + idReportForester +
                        " подтверждено в базе данных");

                PreparedStatement one = connection.prepareStatement(cleaningF);
                one.setInt(1, idReportForester);
                one.executeUpdate();
                PreparedStatement two = connection.prepareStatement(cleaningO);
                two.setInt(1, ownerId);
                two.executeUpdate();
                one.close();
                two.close();
            }else {
                System.err.println("Отчета под Id=" + idReportForester + " не существует");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}