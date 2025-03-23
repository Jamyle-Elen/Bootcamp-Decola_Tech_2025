package br.com.dio.service;

import br.com.dio.dto.BoardColumnInfoDTO;
import br.com.dio.dto.CardDetailsDTO;
import br.com.dio.exception.CardBlockedException;
import br.com.dio.exception.CardFinishedException;
import br.com.dio.exception.EntityNotFoundException;
import br.com.dio.persistence.dao.BlockDAO;
import br.com.dio.persistence.dao.CardDAO;
import br.com.dio.persistence.entity.CardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static br.com.dio.persistence.entity.BoardColumnKindEnum.CANCEL;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.FINAL;

@AllArgsConstructor
public class CardService {

    private final Connection connection;

    public CardEntity create(final CardEntity entity) throws SQLException {
        try {
            CardDAO dao = new CardDAO(connection);
            dao.insert(entity);
            connection.commit();
            return entity;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }

    public void moveToNextColumn(final Long cardId, final List<BoardColumnInfoDTO> boardColumnsInfo)
            throws SQLException {
        try {
            CardDAO dao = new CardDAO(connection);
            Optional<CardDetailsDTO> optional = dao.findById(cardId);
            CardDetailsDTO dto = optional.orElseThrow(
                    () -> new EntityNotFoundException("O card de id " + cardId + " não foi encontrado"));

            if (dto.blocked()) {
                String message = "O card " + cardId + " está bloqueado, é necesário desbloquea-lo para mover";
                throw new CardBlockedException(message);
            }

            BoardColumnInfoDTO currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(dto.columnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("O card informado pertence a outro board"));

            if (currentColumn.kind().equals(FINAL)) {
                throw new CardFinishedException("O card já foi finalizado");
            }

            BoardColumnInfoDTO nextColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.order() == currentColumn.order() + 1)
                    .findFirst().orElseThrow(() -> new IllegalStateException("O card está cancelado"));

            dao.moveToColumn(nextColumn.id(), cardId);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }

    public void cancel(final Long cardId, final Long cancelColumnId,
            final List<BoardColumnInfoDTO> boardColumnsInfo) throws SQLException {
        try {
            CardDAO dao = new CardDAO(connection);
            Optional<CardDetailsDTO> optional = dao.findById(cardId);
            CardDetailsDTO dto = optional.orElseThrow(
                    () -> new EntityNotFoundException("O card de id " + cardId + " não foi encontrado"));

            if (dto.blocked()) {
                String message = "O card " + cardId + " está bloqueado, é necesário desbloquea-lo para mover";
                throw new CardBlockedException(message);
            }

            BoardColumnInfoDTO currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(dto.columnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("O card informado pertence a outro board"));

            if (currentColumn.kind().equals(FINAL)) {
                throw new CardFinishedException("O card já foi finalizado");
            }

            boardColumnsInfo.stream()
                    .filter(bc -> bc.order() == currentColumn.order() + 1)
                    .findFirst().orElseThrow(() -> new IllegalStateException("O card está cancelado"));

            dao.moveToColumn(cancelColumnId, cardId);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }

    public void block(final Long id, final String reason, final List<BoardColumnInfoDTO> boardColumnsInfo)
            throws SQLException {
        try {
            CardDAO dao = new CardDAO(connection);
            Optional<CardDetailsDTO> optional = dao.findById(id);
            CardDetailsDTO dto = optional.orElseThrow(
                    () -> new EntityNotFoundException("O card de id " + id + " não foi encontrado"));

            if (dto.blocked()) {
                String message = "O card " + id + " já está bloqueado";
                throw new CardBlockedException(message);
            }

            BoardColumnInfoDTO currentColumn = boardColumnsInfo.stream()
                    .filter(bc -> bc.id().equals(dto.columnId()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Coluna não encontrada"));

            if (currentColumn.kind().equals(FINAL) || currentColumn.kind().equals(CANCEL)) {
                String message = "O card está em uma coluna do tipo " + currentColumn.kind()
                        + " e não pode ser bloqueado";
                throw new IllegalStateException(message);
            }

            BlockDAO blockDAO = new BlockDAO(connection);
            blockDAO.block(reason, id);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }

    public void unblock(final Long id, final String reason) throws SQLException {
        try {
            CardDAO dao = new CardDAO(connection);
            Optional<CardDetailsDTO> optional = dao.findById(id);
            CardDetailsDTO dto = optional.orElseThrow(
                    () -> new EntityNotFoundException("O card de id " + id + " não foi encontrado"));

            if (!dto.blocked()) {
                String message = "O card " + id + " não está bloqueado";
                throw new CardBlockedException(message);
            }

            BlockDAO blockDAO = new BlockDAO(connection);
            blockDAO.unblock(reason, id);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        }
    }
}
