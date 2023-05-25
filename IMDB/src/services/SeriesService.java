package services;

import models.Series;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeriesService {
    private static SeriesService instance;
    Connection connection;
    public SeriesService() {
        this.connection = DatabaseConnection.getDbConnection();
    }
    public static SeriesService getInstance() {
        if (instance == null) {
            instance = new SeriesService();
        }
        return instance;
    }

    public boolean createSeries(Series series) {
        ShowService showService = new ShowService();
        try {
            boolean showCreated = showService.createShow(series);
            if(showCreated){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO series (episodesNR, episodeDuration, idShow) VALUES (?, ?, ?)");
                statement.setInt(1, series.getEpisodesNr());
                statement.setInt(2, series.getEpisodeDuration());
                statement.setInt(3, series.getId());
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
    public boolean updateSeries(Series series) {
        ShowService showService = new ShowService();
        try {
            boolean updateShow = showService.updateShow(series);
            if(updateShow){
                PreparedStatement statement = connection.prepareStatement("UPDATE series SET episodesNr = ?, episodeDuration = ? WHERE idShow = ?");
                statement.setInt(1, series.getEpisodesNr());
                statement.setInt(2, series.getEpisodeDuration());
                statement.setInt(3, series.getId());
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
