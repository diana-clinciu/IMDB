package services;

import models.Film;

import java.sql.*;

public class FilmService {
    private static FilmService instance;
    Connection connection;
    public FilmService() {
        this.connection = DatabaseConnection.getDbConnection();
    }
    public static FilmService getInstance() {
        if (instance == null) {
            instance = new FilmService();
        }
        return instance;
    }

    public boolean createFilm(Film film) {
        ShowService showService = new ShowService();
        try {
            boolean showCreated = showService.createShow(film);
            if(showCreated){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO film (duration,idShow) VALUES (?, ?)");
                statement.setInt(1, film.getDuration());
                statement.setInt(2, film.getId());
                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateFilm(Film film) {
        ShowService showService = new ShowService();
        try {
            boolean updateShow = showService.updateShow(film);
            if(updateShow){
                PreparedStatement statement = connection.prepareStatement("UPDATE film SET duration = ? WHERE idShow = ?");
                statement.setInt(1, film.getDuration());
                statement.setInt(2, film.getId());
                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
