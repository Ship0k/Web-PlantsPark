package by.park.dao;

import by.park.entity.object.Plant;

/*
 * Interface for the park owner, has the following methods:
 *
 * taskLanding, taskTreatment, taskArtProcessing, taskDestruction - these methods
 * add the corresponding tasks to the list of tasks in the database
 *
 * deleteTask - deletes a task from the task list (in case of an error)
 * insertPlant - puts a new plant in the database and returns the assigned Id
 * findPlant - search for a plant in the database, returns the found plant as an object
 * deletePlant - delete a plant and information about it from the database
 * viewForesterReport - to view the report of the forester on the implementation of specified tasks
 * confirmReport - records completed tasks in the database by the number of the forester's report,
 * then clears the task list and report
 */
public interface OwnerDAO {
    void taskLanding(Plant plant);
    void taskTreatment(Plant plant);
    void taskArtProcessing(Plant plant);
    void taskDestruction(Plant plant);
    void deleteTask(int idTask);
    int insertPlant(String title);
    Plant findPlant(int idPlant);
    void deletePlant(int idPlant);
    void viewForesterReport();
    void confirmReport(int idReportForester);
}
