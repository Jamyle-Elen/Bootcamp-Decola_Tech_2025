package br.com.dio.persistence.migration;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;

@AllArgsConstructor
public class MigrationStrategy {

    private final Connection connection;

    public void executeMigration() {
        PrintStream originalOut = System.out;
        PrintStream originalErr = System.err;

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("liquibase.log");
            System.setOut(new PrintStream(fos));
            System.setErr(new PrintStream(fos));

            Connection dbConnection = null;
            JdbcConnection jdbcConnection = null;

            try {
                dbConnection = getConnection();
                jdbcConnection = new JdbcConnection(dbConnection);

                Liquibase liquibase = new Liquibase(
                        "/db/changelog/db.changelog-master.yml",
                        new ClassLoaderResourceAccessor(),
                        jdbcConnection);
                liquibase.update();
            } catch (SQLException | LiquibaseException e) {
                e.printStackTrace();
                System.setErr(originalErr);
            } finally {
                if (jdbcConnection != null) {
                    try {
                        jdbcConnection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (dbConnection != null) {
                    try {
                        dbConnection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.setOut(originalOut);
            System.setErr(originalErr);
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
