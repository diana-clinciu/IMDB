package services;

import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowService {
    private static ShowService instance;
    Connection connection;

    public ShowService() {
        this.connection = DatabaseConnection.getDbConnection();
    }

    public static ShowService getInstance() {
        if (instance == null) {
            instance = new ShowService();
        }
        return instance;
    }

    public List<Show> getAllShows() {
        List<Show> shows = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT s.idShow, s.name as showName, s.releaseYear, s.description, c.idCategory, c.name as categoryName " +
//                            "a.* "+
                            "FROM `show` s " +
                            "LEFT JOIN category_show cs ON cs.idShow = s.idShow " +
                            "LEFT JOIN category c on cs.idCategory = c.idCategory "
//                            "LEFT JOIN actor_show sa on sa.idShow = s.idShow " +
//                            "LEFT JOIN actor a on sa.idActor = a.idActor "
                    );
            while (resultSet.next()) {
                int idShow = resultSet.getInt("idShow");
                String showName = resultSet.getString("showName");
                int releaseYear = resultSet.getInt("releaseYear");
                String description = resultSet.getString("description");

                Show show;
                try {
                    show = Show.findById(idShow, shows);
                } catch (Exception e) {
                    PreparedStatement statement1 = connection.prepareStatement("SELECT film.duration FROM film WHERE film.idShow = ?");
                    statement1.setInt(1, idShow);
                    ResultSet resultSet1 = statement1.executeQuery();
                    if(resultSet1.next()){
                        // este film
                        int duration = resultSet1.getInt("duration");
                        show = new Film(idShow, showName, releaseYear, description, duration);
                    }
                    else{
                        // nu este film
                        PreparedStatement statement2 = connection.prepareStatement("SELECT episodesNr, episodeDuration FROM series WHERE series.idShow = ?");
                        statement2.setInt(1, idShow);
                        ResultSet resultSet2 = statement2.executeQuery();
                        resultSet2.next();
                        int episodesNr = resultSet2.getInt("episodesNr");
                        int episodeDuration = resultSet2.getInt("episodeDuration");
                        show = new Series(idShow, showName, releaseYear, description, episodesNr, episodeDuration);
                    }
                    shows.add(show);
                }

                String categoryName = resultSet.getString("categoryName");
                int idCategory = resultSet.getInt("idCategory");
                List<Category> categories = new ArrayList<>();
                categories = show.getCategories();
                if (idCategory != 0) {
                    Category category = new Category(idCategory, categoryName);
                    categories.add(category);
                    show.setCategories(categories);
                }

//                String firstName = resultSet.getString("firstName");
//                String lastName = resultSet.getString("lastName");
//                int idActor = resultSet.getInt("idActor");
//                int age = resultSet.getInt("age");
//                List<Actor> actors = new ArrayList<>();
//                actors = show.getActors();
//                if (idActor != 0) {
//                    Actor actor = new Actor(idActor, firstName, lastName, age);
//                    actors.add(actor);
//                    show.setActors(actors);
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shows;
    }

    public boolean addCategory(Show show, Category category){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO category_show (idCategory, idShow) VALUES (?, ?)");
            statement.setInt(2, show.getId());
            statement.setInt(1, category.getId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addActor(Show show, Actor actor){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO actor_show (idActor, idShow) VALUES (?, ?)");
            statement.setInt(2, show.getId());
            statement.setInt(1, actor.getId());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createShow(Show show) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO `show` (idShow, name, releaseYear, description) VALUES (?, ?,?,?)");
            statement.setInt(1, show.getId());
            statement.setString(2, show.getName());
            statement.setInt(3, show.getReleaseYear());
            statement.setString(4, show.getDescription());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateShow(Show show) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE `show` SET name = ?, releaseYear = ?, description = ?  WHERE idShow = ?");
            statement.setString(1, show.getName());
            statement.setInt(2, show.getReleaseYear());
            statement.setString(3, show.getDescription());
            statement.setInt(4, show.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteShow(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `show` WHERE idShow = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
