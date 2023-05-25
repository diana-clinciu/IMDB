package models;

import java.util.Scanner;

public class Category extends Entity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("Name: ");
        this.name = scanner.nextLine();
    }

    public Category() {

    }
    public Category(int id, String name){
        super(id);
        this.name=name;
    }

    public void print() {
        System.out.println("Category id: " + this.id);
        System.out.println("Name: " + this.name);
    }

    @Override
    public String toString() {
        return "Category{" +
                super.toString() +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

}
