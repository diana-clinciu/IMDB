package services;

import models.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    private static ReviewService instance;
    Connection connection;
    public ReviewService() {
        this.connection = DatabaseConnection.getDbConnection();
    }
    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    //TODO AFISEAZA SI USERII REVIEW-URILOR (CREATE TAB USER)
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM review");
            while (resultSet.next()) {
                Review review = new Review();
                review.setId(resultSet.getInt("idReview"));
                review.setGrade(resultSet.getInt("grade"));
                review.setUserId(resultSet.getInt("userId"));
                review.setDescription(resultSet.getString("description"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }
    public boolean createReview(Review review) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO review (idReview, grade, description, userId) VALUES (?, ?, ?, ?)");
            statement.setInt(1, review.getId());
            statement.setInt(2, review.getGrade());
            statement.setString(3, review.getDescription());
            statement.setInt(4, review.getUserId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateReview(Review review) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE review SET grade = ?, description = ?, userId = ?  WHERE idReview = ?");
            statement.setInt(1, review.getGrade());
            statement.setString(2, review.getDescription());
            statement.setInt(3, review.getUserId());
            statement.setInt(4, review.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteReview(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM review WHERE idReview = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
