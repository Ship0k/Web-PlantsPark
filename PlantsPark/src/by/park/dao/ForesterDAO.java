package by.park.dao;

/*
 * Forester interface announces the following methods:
 * viewTask - view tasks for the owner
 * performTask - perform the task by the number from the task list (this creates a report sheet)
 * viewRеportList - view your report
 * deleteReport - delete your report under the specified Id (if the forester made an error)
 */
public interface ForesterDAO {
    void viewTask();
    void performTask(int idOwnerTask);
    void viewRеportList();
    void deleteReport(int foresterReportId);
}
