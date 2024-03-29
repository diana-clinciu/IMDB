package models;

import java.util.Scanner;

public class Review extends Entity {
    private int grade;
    private String description;
    private int userId;

    public Review(Review review) {
        super(review);
        this.grade = review.grade;
        this.description = review.description;
        this.userId = review.userId;
    }

    public Review() {

    }

    @Override
    public void read(Scanner scanner) {
        super.read(scanner);
        System.out.println("User id: ");
        this.userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Grade: ");
        this.grade = Integer.parseInt(scanner.nextLine());
        System.out.println("Description: ");
        this.description = scanner.nextLine();
    }

    public void print() {
        System.out.println("Review id: " + this.id);
        System.out.println("User id: " + this.userId);
        System.out.println("Grade: " + this.grade);
        System.out.println("Description: " + this.description);
    }

    public int getGrade() {
        return grade;
    }

    public int getUserId() {
        return userId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Review{" +
                super.toString() +
                "grade=" + grade +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
