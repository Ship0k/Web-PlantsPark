package by.park.entity;

import java.util.Objects;

/*
 * This class stores the number and type of task,
 * as well as the name of the plant on which you want to perform the specified work
 */
public class Owner {
    private int id;
    private String plant;
    private String task;

    public Owner(){

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
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return getId() == owner.getId() &&
                Objects.equals(getPlant(), owner.getPlant()) &&
                Objects.equals(getTask(), owner.getTask());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPlant(), getTask());
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", plant='" + plant + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
