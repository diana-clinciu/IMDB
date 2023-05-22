package services;
import models.Actor;
import models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class ActorService {
    private static ActorService instance;
    private Connection connection;
    private ActorService() {
        connection = DatabaseConnection.getDbConnection();
    }
    public static ActorService getInstance() {
        if (instance == null) {
            instance = new ActorService();
        }
        return instance;
    }

    public List<Actor> getAllActors() {
        List<Actor> actors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT a.idActor, a.lastName, a.firstName, a.age, aw.name AS awardName " +
                    "FROM actor a " +
                    "LEFT JOIN award aw ON a.idActor = aw.idActor");
            while (resultSet.next()) {
                int idActor = resultSet.getInt("idActor");
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                int age = resultSet.getInt("age");

                Actor actor = findActorById(actors, idActor);

                if (actor == null) {
                    actor = new Actor(idActor, lastName, firstName, age);
                    actors.add(actor);
                }
                String awardName = resultSet.getString("awardName");
                List<String> awards = new ArrayList<>();
                awards = actor.getAwards();
                if (awardName != null ) {
                    awards.add(awardName);
                    actor.setAwards(awards);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }
    private Actor findActorById(List<Actor> actors, int idActor) {
        for (Actor actor : actors) {
            if (actor.getId() == idActor) {
                return actor;
            }
        }
        return null;
    }
    public boolean addAwardToActor(int idAward, int idActor, String awardName) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO award (idAward, name, idActor) VALUES (?, ?, ?)");
            statement.setInt(1, idAward);
            statement.setString(2, awardName);
            statement.setInt(3, idActor);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean createActor(Actor actor) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO actor (idActor, lastName,firstName,age) VALUES (?, ?, ?, ?)");
            statement.setInt(1, actor.getId());
            statement.setString(2, actor.getLastName());
            statement.setString(3, actor.getFirstName());
            statement.setInt(4, actor.getAge());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateActor(Actor actor) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE actor SET lastName = ?, firstName = ?, age = ? WHERE idActor = ?");
            statement.setString(1, actor.getLastName());
            statement.setString(2, actor.getFirstName());
            statement.setInt(3, actor.getAge());
            statement.setInt(4, actor.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteActor(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM actor WHERE idActor = ?");
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
