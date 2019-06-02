USE plantsPark
GO

-- View all ****
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
ORDER BY p.Id
SELECT * FROM Tasks
SELECT * FROM ListOwnerTasks
SELECT * FROM ListForesterReports
GO

--Forester **** viewTask()
SELECT lt.Id, Title + ' ' + CONVERT(nvarchar(4), p.Id) Plant, TaskType 
FROM ListOwnerTasks lt 
JOIN Plants p ON lt.PlantId = p.ID 
JOIN Tasks t ON lt.TaskId = t.ID 
ORDER BY lt.Id
GO

--Forester **** viewRÂportList()
SELECT lr.Id, Title + ' ' + CONVERT(nvarchar(4), p.Id) Plant, TaskType 
FROM ListForesterReports lr 
JOIN ListOwnerTasks lt ON lr.OwnerTaskId = lt.Id 
JOIN Plants p ON lt.PlantId = p.ID 
JOIN Tasks t ON lt.TaskId = t.ID 
ORDER BY lr.Id
GO

--Forester **** performTask(int idOwnerTask)
INSERT ListForesterReports (OwnerTaskId) VALUES (?)
GO

--Forester **** deleteReport(int foresterReportId)
DELETE FROM ListForesterReports WHERE Id = ?
GO

--Owner **** insertPlant(String title) 
INSERT Plants (Title) VALUES (?)                    --addSql
GO
SELECT TOP 1 Id FROM Plants ORDER BY ID DESC        --extractSql
GO

--Owner **** findPlant(int idPlant)
SELECT * FROM Plants WHERE Id = ?
GO

--Owner **** deletePlant(int idPlant)
DELETE FROM Plants WHERE Id = ?
GO

--Owner **** taskLanding(Plant plant)
INSERT ListOwnerTasks (PlantId, TaskId) VALUES (?, 1)
GO

--Owner **** taskArtProcessing(Plant plant)
INSERT ListOwnerTasks (PlantId, TaskId) VALUES (?, 2)
GO

--Owner **** taskTreatment(Plant plant)
INSERT ListOwnerTasks (PlantId, TaskId) VALUES (?, 3)
GO

--Owner **** taskDestruction(Plant plant)
INSERT ListOwnerTasks (PlantId, TaskId) VALUES (?, 4)
GO

--Owner **** deleteTask(int idTask)
DELETE FROM ListOwnerTasks WHERE Id = ? 
GO

--Owner **** viewForesterReport()
SELECT lr.Id, Title + ' ' + CONVERT(nvarchar(4), p.Id) Plant, TaskType 
FROM ListForesterReports lr 
JOIN ListOwnerTasks lt ON lr.OwnerTaskId = lt.Id 
JOIN Plants p ON lt.PlantId = p.ID 
JOIN Tasks t ON lt.TaskId = t.ID 
ORDER BY lr.Id
GO

--Owner **** confirmReport(int idReportForester)
SELECT lt.Id, PlantId, TaskId                                                 --extractSql
FROM ListForesterReports lr 
JOIN ListOwnerTasks lt ON lr.OwnerTaskId = lt.Id 
WHERE lr.Id = ?
GO

UPDATE PlantsDetails SET LandingDate = CURRENT_TIMESTAMP WHERE ID = ?        --landingSql
UPDATE PlantsDetails SET ArtWorkN = ArtWorkN + 1 WHERE ID = ?                --artSql
UPDATE PlantsDetails SET TreatmentN = TreatmentN + 1 WHERE ID = ?            --treatmentSql
UPDATE PlantsDetails SET DestructionDate = CURRENT_TIMESTAMP WHERE ID = ?    --deleteSql
GO

DELETE FROM ListForesterReports WHERE Id = ?                                 --cleaningF
DELETE FROM ListOwnerTasks WHERE Id = ?                                      --cleaningO
GO

--PlantsInfo **** viewAllPlants()
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE LandingDate <> 0 
ORDER BY p.Id
GO

--PlantsInfo **** viewPlantById(int idPlant)
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id
WHERE p.Id = ?
GO

--PlantsInfo **** viewDestroyedPlants()
SELECT p.Id, Title, LandingDate, ArtWorkN, TreatmentN, DestructionDate 
FROM Plants p 
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE DestructionDate <> 0 
ORDER BY DestructionDate
GO

--PlantsInfo **** quantityPlantsByName(String title)
SELECT COUNT(p.Id) Quantity, Title FROM Plants p
JOIN PlantsDetails pd ON pd.Id = p.Id 
WHERE Title = ? AND LandingDate <> 0 AND DestructionDate IS NULL
GROUP BY Title
GO

--PlantsInfo **** quantityArtProcessed()
SELECT SUM(ArtWorkN) Quantity FROM PlantsDetails
GO

--PlantsInfo **** quantity—ured()
SELECT SUM(TreatmentN) Quantity FROM PlantsDetails
GO
