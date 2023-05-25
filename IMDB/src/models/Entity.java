package models;

import java.util.List;
import java.util.Scanner;

abstract public class Entity {
    protected int id;

    public void read(Scanner scanner) {
        System.out.println("Id ");
        this.id = Integer.parseInt(scanner.nextLine());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entity(int id) {
        this.id = id;
    }

    public Entity(Entity e) {
        this.id = e.id;
    }

    public Entity() {
    }
    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

    static public <T extends Entity> T findById(int id, List<T> array) throws Exception {
        for (T e : array) {
            if (e.getId() == id)
                return e;
        }
        throw new Exception("Entity not found!");
    }
}
