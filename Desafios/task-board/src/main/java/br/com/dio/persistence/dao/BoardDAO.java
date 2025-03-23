package br.com.dio.persistence.dao;

import br.com.dio.persistence.entity.BoardEntity;
import com.mysql.cj.jdbc.StatementImpl;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAO {

    private Connection connection;

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        String sql = "INSERT INTO BOARDS (name) values (?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            if (statement instanceof StatementImpl) {
                StatementImpl impl = (StatementImpl) statement;
                entity.setId(impl.getLastInsertID());
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return entity;
    }

    public void delete(final Long id) throws SQLException {
        String sql = "DELETE FROM BOARDS WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, id);
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        String sql = "SELECT id, name FROM BOARDS WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, id);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                BoardEntity entity = new BoardEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                return Optional.of(entity);
            }
            return Optional.empty();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public boolean exists(final Long id) throws SQLException {
        String sql = "SELECT 1 FROM BOARDS WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, id);
            statement.executeQuery();
            return statement.getResultSet().next();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

}
