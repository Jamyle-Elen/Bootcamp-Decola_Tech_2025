package br.com.dio.service;

import br.com.dio.persistence.dao.BoardColumnDAO;
import br.com.dio.persistence.dao.BoardDAO;
import br.com.dio.persistence.entity.BoardEntity;
import br.com.dio.persistence.entity.BoardColumnEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BoardService {

    private final Connection connection;

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        BoardDAO dao = new BoardDAO(connection);
        BoardColumnDAO boardColumnDAO = new BoardColumnDAO(connection);
        
        try {
            dao.insert(entity);
            
            List<BoardColumnEntity> columns = entity.getBoardColumns().stream()
                .map(c -> {
                    c.setBoard(entity);
                    return c;
                })
                .collect(Collectors.toList());
            
            for (BoardColumnEntity column : columns) {
                boardColumnDAO.insert(column);
            }
            
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        
        return entity;
    }

    public boolean delete(final Long id) throws SQLException {
        BoardDAO dao = new BoardDAO(connection);
        
        try {
            if (!dao.exists(id)) {
                return false;
            }
            
            dao.delete(id);
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
