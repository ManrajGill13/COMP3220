package connection;

import connection.exception.ConnectionBuildException;

public final class ConnectionBuilder {
    private String ip;
    private int port;
    private String database;
    private String user;
    private String password;

    public ConnectionBuilder setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public ConnectionBuilder setPort(int port) {
        this.port = port;
        return this;
    }

    public ConnectionBuilder setDatabase(String database) {
        this.database = database;
        return this;
    }

    public ConnectionBuilder setUser(String user) {
        this.user = user;
        return this;
    }

    public ConnectionBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Connection build() throws ConnectionBuildException {
        if (ip == null) {
            throw new ConnectionBuildException("IP must have a value.");
        } else if (port == 0) {
            throw new ConnectionBuildException("Port must have a value.");
        } else if (database == null) {
            throw new ConnectionBuildException("Database must have a value.");
        } else if (user == null) {
            throw new ConnectionBuildException("User must have a value.");
        } else if (password == null) {
            throw new ConnectionBuildException("Password must have a value.");
        }

        return new Connection(
                ip,
                port,
                database,
                user,
                password
        );
    }
}
