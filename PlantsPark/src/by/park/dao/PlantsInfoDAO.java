package by.park.dao;

import by.park.entity.object.Plant;

/*
 * Interface for viewing information on park plants
 *
 * So far has 6 methods:
 * viewAllPlants - displays all plants except those that have not yet been planted in the park
 * viewPlantById - all information about the plant by Id
 * viewDestroyedPlants - the history of plants that were found in the park
 * quantityPlantsByName - the number of existing (planted and not destroyed) plants in the park by name
 * quantityСured - the number of supports for the treatment of plants of the park
 * quantityArtProcessed - Number of operations for the artistic treatment of the park's plants
 */
public interface PlantsInfoDAO {
    void viewAllPlants();
    Plant viewPlantById(int idPlant);
    void viewDestroyedPlants();
    void quantityPlantsByName(String title);
    void quantityСured();
    void quantityArtProcessed();
}
