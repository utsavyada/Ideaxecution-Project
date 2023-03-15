import java.sql.*;

public class CoinGameDatabase {
    private Connection connection;

    public CoinGameDatabase() {
     
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:coingame.db");
            System.out.println("Connected to database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

     
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, score INTEGER, moves INTEGER, date DATETIME DEFAULT CURRENT_TIMESTAMP)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addGame(String username, int score, int moves) {
        
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO games (username, score, moves) VALUES (?, ?, ?)");
            statement.setString(1, username);
            statement.setInt(2, score);
            statement.setInt(3, moves);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printGames(String username) {
       
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM games WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int score = resultSet.getInt("score");
                int moves = resultSet.getInt("moves");
                String date = resultSet.getString("date");
                System.out.println("Game " + id + ": " + score + " points, " + moves + " moves, played on " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}