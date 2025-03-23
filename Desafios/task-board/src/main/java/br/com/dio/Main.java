package br.com.dio;

import br.com.dio.persistence.migration.MigrationStrategy;
import br.com.dio.ui.MainMenu;

import java.sql.Connection;
import java.sql.SQLException;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;


public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            new MigrationStrategy(connection).executeMigration();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        new MainMenu().execute();
    }

}
