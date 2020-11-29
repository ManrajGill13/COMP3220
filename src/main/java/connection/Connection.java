package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private final String ip;
    private final int port;
    private final String database;
    private final String user;
    private final String password;

    private java.sql.Connection connection;

    Connection(String ip, int port, String database, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public java.sql.Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s:%d/%s", ip, port, database),
                        user,
                        password
                );
                System.out.println("Database connected");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
