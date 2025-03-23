package br.com.dio.persistence.dao;

import br.com.dio.dto.BoardColumnDTO;
import br.com.dio.persistence.entity.BoardColumnEntity;
import br.com.dio.persistence.entity.CardEntity;
import com.mysql.cj.jdbc.StatementImpl;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.dio.persistence.entity.BoardColumnKindEnum.findByName;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
public class BoardColumnDAO {

    private final Connection connection;

    public BoardColumnEntity insert(final BoardColumnEntity entity) throws SQLException {
        String sql = "INSERT INTO BOARDS_COLUMNS (name, `order`, kind, board_id) VALUES (?, ?, ?, ?);";
        int i = 1;

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(i++, entity.getName());
            statement.setInt(i++, entity.getOrder());
            statement.setString(i++, entity.getKind().name());
            statement.setLong(i, entity.getBoard().getId());
            statement.executeUpdate();

            if (statement instanceof StatementImpl) {
                StatementImpl impl = (StatementImpl) statement;
                entity.setId(impl.getLastInsertID());
            }

            return entity;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<BoardColumnEntity> findByBoardId(final Long boardId) throws SQLException {
        List<BoardColumnEntity> entities = new ArrayList<>();
        String sql = "SELECT id, name, `order`, kind FROM BOARDS_COLUMNS WHERE board_id = ? ORDER BY `order`";

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, boardId);
            statement.executeQuery();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                BoardColumnEntity entity = new BoardColumnEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setOrder(resultSet.getInt("order"));
                entity.setKind(findByName(resultSet.getString("kind")));
                entities.add(entity);
            }

            return entities;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public List<BoardColumnDTO> findByBoardIdWithDetails(final Long boardId) throws SQLException {
        List<BoardColumnDTO> dtos = new ArrayList<>();
        String sql = "SELECT bc.id, " +
                "       bc.name, " +
                "       bc.kind, " +
                "       (SELECT COUNT(c.id) " +
                "               FROM CARDS c " +
                "              WHERE c.board_column_id = bc.id) cards_amount " +
                "  FROM BOARDS_COLUMNS bc " +
                " WHERE board_id = ? " +
                " ORDER BY `order`;";

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, boardId);
            statement.executeQuery();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                BoardColumnDTO dto = new BoardColumnDTO(
                        resultSet.getLong("bc.id"),
                        resultSet.getString("bc.name"),
                        findByName(resultSet.getString("bc.kind")),
                        resultSet.getInt("cards_amount"));
                dtos.add(dto);
            }

            return dtos;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public Optional<BoardColumnEntity> findById(final Long boardId) throws SQLException {
        String sql = "SELECT bc.name, " +
                "       bc.kind, " +
                "       c.id, " +
                "       c.title, " +
                "       c.description " +
                "  FROM BOARDS_COLUMNS bc " +
                "  LEFT JOIN CARDS c " +
                "    ON c.board_column_id = bc.id " +
                " WHERE bc.id = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, boardId);
            statement.executeQuery();

            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                BoardColumnEntity entity = new BoardColumnEntity();
                entity.setName(resultSet.getString("bc.name"));
                entity.setKind(findByName(resultSet.getString("bc.kind")));

                do {
                    if (isNull(resultSet.getString("c.title"))) {
                        break;
                    }

                    CardEntity card = new CardEntity();
                    card.setId(resultSet.getLong("c.id"));
                    card.setTitle(resultSet.getString("c.title"));
                    card.setDescription(resultSet.getString("c.description"));
                    entity.getCards().add(card);
                } while (resultSet.next());

                return Optional.of(entity);
            }

            return Optional.empty();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
}
