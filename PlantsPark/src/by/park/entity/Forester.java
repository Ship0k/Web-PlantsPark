package by.park.entity;

import java.util.Objects;

/*
 * This class stores the completed task under a certain number,
 * as well as the name of the plant on which the work was carried out
 */
public class Forester {
    private int id;
    private String plant;
    private String task;

    public Forester(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Forester)) return false;
        Forester forester = (Forester) o;
        return getId() == forester.getId() &&
                Objects.equals(getPlant(), forester.getPlant()) &&
                Objects.equals(getTask(), forester.getTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlant(), getTask());
    }

    @Override
    public String toString() {
        return "Forester{" +
                "id=" + id +
                ", plant='" + plant + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
