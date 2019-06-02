package by;

import by.park.entity.object.Plant;
import by.park.dao.service.PlantsListDao;

/*
 * Main class for to check the functionality
 *
 * @author     Anatoli Shipkou
 */
public class MainProgram {
    public static void main(String[] args) {
        //DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.SQLSERVER);
        //OwnerDAO ownerDAO = factory.getOwnerDAO();
        //ForesterDAO foresterDAO = factory.getForesterDAO();
        //PlantsInfoDAO plantsInfoDAO = factory.getPlantInfoDAO();

        //Plant plant;
        //ownerDAO.insertPlant("Ольха");
        //plant = ownerDAO.findPlant(73);
        //ownerDAO.taskLanding(plant);
        //foresterDAO.viewTask();
        //foresterDAO.performTask(7);

        //ownerDAO.confirmReport(5);
        //ownerDAO.viewForesterReport();
        //foresterDAO.viewTask();
        //plantsInfoDAO.viewPlantById(1);

        Plant curPlant = PlantsListDao.getInstance().getCurPlant();
        System.out.println(curPlant);




    }
}
