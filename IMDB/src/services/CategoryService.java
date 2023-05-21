package services;
import models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategoryService {
    private static CategoryService instance;
    Connection connection;
    public CategoryService() {
        this.connection = DatabaseConnection.getDbConnection();
    }
    public static CategoryService getInstance() {
        if (instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("idCategory"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    public boolean createCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category (idCategory, name) VALUES (?, ?)");
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateCategory(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE category SET name = ? WHERE idCategory = ?");
            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCategory(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM category WHERE idCategory = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
