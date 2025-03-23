package br.com.dio.ui;

import br.com.dio.dto.BoardColumnInfoDTO;
import br.com.dio.dto.BoardDetailsDTO;
import br.com.dio.dto.CardDetailsDTO;
import br.com.dio.persistence.entity.BoardColumnEntity;
import br.com.dio.persistence.entity.BoardEntity;
import br.com.dio.persistence.entity.CardEntity;
import br.com.dio.service.BoardColumnQueryService;
import br.com.dio.service.BoardQueryService;
import br.com.dio.service.CardQueryService;
import br.com.dio.service.CardService;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;

@AllArgsConstructor
public class BoardMenu {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    private final BoardEntity entity;

    public void execute() {
        try {
            System.out.printf("Bem vindo ao board %s, selecione a operação desejada\n", entity.getId());
            int option = -1;
            while (option != 9) {
                System.out.println("1 - Criar um card");
                System.out.println("2 - Mover um card");
                System.out.println("3 - Bloquear um card");
                System.out.println("4 - Desbloquear um card");
                System.out.println("5 - Cancelar um card");
                System.out.println("6 - Ver board");
                System.out.println("7 - Ver coluna com cards");
                System.out.println("8 - Ver card");
                System.out.println("9 - Voltar para o menu anterior um card");
                System.out.println("10 - Sair");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        createCard();
                        break;
                    case 2:
                        moveCardToNextColumn();
                        break;
                    case 3:
                        blockCard();
                        break;
                    case 4:
                        unblockCard();
                        break;
                    case 5:
                        cancelCard();
                        break;
                    case 6:
                        showBoard();
                        break;
                    case 7:
                        showColumn();
                        break;
                    case 8:
                        showCard();
                        break;
                    case 9:
                        System.out.println("Voltando para o menu anterior");
                        break;
                    case 10:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida, informe uma opção do menu");
                        break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }

    private void createCard() throws SQLException {
        CardEntity card = new CardEntity();
        System.out.println("Informe o título do card");
        card.setTitle(scanner.next());
        System.out.println("Informe a descrição do card");
        card.setDescription(scanner.next());
        card.setBoardColumn(entity.getInitialColumn());

        Connection connection = null;
        try {
            connection = getConnection();
            new CardService(connection).create(card);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void moveCardToNextColumn() throws SQLException {
        System.out.println("Informe o id do card que deseja mover para a próxima coluna");
        Long cardId = scanner.nextLong();

        List<BoardColumnInfoDTO> boardColumnsInfo = entity.getBoardColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .collect(Collectors.toList());

        Connection connection = null;
        try {
            connection = getConnection();
            new CardService(connection).moveToNextColumn(cardId, boardColumnsInfo);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void blockCard() throws SQLException {
        System.out.println("Informe o id do card que será bloqueado");
        Long cardId = scanner.nextLong();
        System.out.println("Informe o motivo do bloqueio do card");
        String reason = scanner.next();

        List<BoardColumnInfoDTO> boardColumnsInfo = entity.getBoardColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .collect(Collectors.toList());

        Connection connection = null;
        try {
            connection = getConnection();
            new CardService(connection).block(cardId, reason, boardColumnsInfo);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void unblockCard() throws SQLException {
        System.out.println("Informe o id do card que será desbloqueado");
        Long cardId = scanner.nextLong();
        System.out.println("Informe o motivo do desbloqueio do card");
        String reason = scanner.next();

        Connection connection = null;
        try {
            connection = getConnection();
            new CardService(connection).unblock(cardId, reason);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void cancelCard() throws SQLException {
        System.out.println("Informe o id do card que deseja mover para a coluna de cancelamento");
        Long cardId = scanner.nextLong();

        BoardColumnEntity cancelColumn = entity.getCancelColumn();
        List<BoardColumnInfoDTO> boardColumnsInfo = entity.getBoardColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .collect(Collectors.toList());

        Connection connection = null;
        try {
            connection = getConnection();
            new CardService(connection).cancel(cardId, cancelColumn.getId(), boardColumnsInfo);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void showBoard() throws SQLException {
        Connection connection = null;
        try {
            connection = getConnection();
            Optional<BoardDetailsDTO> optional = new BoardQueryService(connection).showBoardDetails(entity.getId());

            if (optional.isPresent()) {
                BoardDetailsDTO b = optional.get();
                System.out.printf("Board [%s,%s]\n", b.id(), b.name());

                for (br.com.dio.dto.BoardColumnDTO c : b.columns()) {
                    System.out.printf("Coluna [%s] tipo: [%s] tem %s cards\n", c.name(), c.kind(), c.cardsAmount());
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void showColumn() throws SQLException {
        List<Long> columnsIds = entity.getBoardColumns().stream()
                .map(BoardColumnEntity::getId)
                .collect(Collectors.toList());

        Long selectedColumnId = -1L;
        while (!columnsIds.contains(selectedColumnId)) {
            System.out.printf("Escolha uma coluna do board %s pelo id\n", entity.getName());

            for (BoardColumnEntity c : entity.getBoardColumns()) {
                System.out.printf("%s - %s [%s]\n", c.getId(), c.getName(), c.getKind());
            }

            selectedColumnId = scanner.nextLong();
        }

        Connection connection = null;
        try {
            connection = getConnection();
            Optional<BoardColumnEntity> column = new BoardColumnQueryService(connection).findById(selectedColumnId);

            if (column.isPresent()) {
                BoardColumnEntity co = column.get();
                System.out.printf("Coluna %s tipo %s\n", co.getName(), co.getKind());

                for (CardEntity ca : co.getCards()) {
                    System.out.printf("Card %s - %s\nDescrição: %s\n",
                            ca.getId(), ca.getTitle(), ca.getDescription());
                }
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void showCard() throws SQLException {
        System.out.println("Informe o id do card que deseja visualizar");
        Long selectedCardId = scanner.nextLong();

        Connection connection = null;
        try {
            connection = getConnection();
            Optional<CardDetailsDTO> cardOptional = new CardQueryService(connection).findById(selectedCardId);

            if (cardOptional.isPresent()) {
                CardDetailsDTO c = cardOptional.get();
                System.out.printf("Card %s - %s.\n", c.id(), c.title());
                System.out.printf("Descrição: %s\n", c.description());
                System.out.println(c.blocked() ? "Está bloqueado. Motivo: " + c.blockReason() : "Não está bloqueado");
                System.out.printf("Já foi bloqueado %s vezes\n", c.blocksAmount());
                System.out.printf("Está no momento na coluna %s - %s\n", c.columnId(), c.columnName());
            } else {
                System.out.printf("Não existe um card com o id %s\n", selectedCardId);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
