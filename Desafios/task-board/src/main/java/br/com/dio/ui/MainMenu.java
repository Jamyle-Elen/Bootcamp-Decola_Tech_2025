package br.com.dio.ui;

import br.com.dio.persistence.entity.BoardColumnEntity;
import br.com.dio.persistence.entity.BoardColumnKindEnum;
import br.com.dio.persistence.entity.BoardEntity;
import br.com.dio.service.BoardQueryService;
import br.com.dio.service.BoardService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static br.com.dio.persistence.config.ConnectionConfig.getConnection;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.CANCEL;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.FINAL;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.INITIAL;
import static br.com.dio.persistence.entity.BoardColumnKindEnum.PENDING;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in).useDelimiter("\n");

    public void execute() {
        try {
            System.out.println("Bem vindo ao gerenciador de boards, escolha a opção desejada");
            int option = -1;
            while (option != 4) {
                System.out.println("1 - Criar um novo board");
                System.out.println("2 - Selecionar um board existente");
                System.out.println("3 - Excluir um board");
                System.out.println("4 - Sair");

                try {
                    String input = scanner.nextLine().trim();
                    option = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, digite um número válido.");
                    continue;
                }

                switch (option) {
                    case 1:
                        createBoard();
                        break;
                    case 2:
                        selectBoard();
                        break;
                    case 3:
                        deleteBoard();
                        break;
                    case 4:
                        System.out.println("Saindo do sistema");
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

    private void createBoard() throws SQLException {
        BoardEntity entity = new BoardEntity();
        System.out.println("Informe o nome do seu board");
        entity.setName(scanner.next());

        System.out.println("Seu board terá colunas além das 3 padrões? Se sim informe quantas, senão digite '0'");
        int additionalColumns = scanner.nextInt();

        List<BoardColumnEntity> columns = new ArrayList<>();

        System.out.println("Informe o nome da coluna inicial do board");
        String initialColumnName = scanner.next();
        BoardColumnEntity initialColumn = createColumn(initialColumnName, INITIAL, 0);
        columns.add(initialColumn);

        for (int i = 0; i < additionalColumns; i++) {
            System.out.println("Informe o nome da coluna de tarefa pendente do board");
            String pendingColumnName = scanner.next();
            BoardColumnEntity pendingColumn = createColumn(pendingColumnName, PENDING, i + 1);
            columns.add(pendingColumn);
        }

        System.out.println("Informe o nome da coluna final");
        String finalColumnName = scanner.next();
        BoardColumnEntity finalColumn = createColumn(finalColumnName, FINAL, additionalColumns + 1);
        columns.add(finalColumn);

        System.out.println("Informe o nome da coluna de cancelamento do baord");
        String cancelColumnName = scanner.next();
        BoardColumnEntity cancelColumn = createColumn(cancelColumnName, CANCEL, additionalColumns + 2);
        columns.add(cancelColumn);

        entity.setBoardColumns(columns);

        Connection connection = null;
        try {
            connection = getConnection();
            BoardService service = new BoardService(connection);
            service.insert(entity);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void selectBoard() throws SQLException {
        System.out.println("Informe o id do board que deseja selecionar");
        Long id = scanner.nextLong();

        Connection connection = null;
        try {
            connection = getConnection();
            BoardQueryService queryService = new BoardQueryService(connection);
            Optional<BoardEntity> optional = queryService.findById(id);

            if (optional.isPresent()) {
                BoardEntity board = optional.get();
                new BoardMenu(board).execute();
            } else {
                System.out.printf("Não foi encontrado um board com id %s\n", id);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void deleteBoard() throws SQLException {
        System.out.println("Informe o id do board que será excluido");
        Long id = scanner.nextLong();

        Connection connection = null;
        try {
            connection = getConnection();
            BoardService service = new BoardService(connection);

            if (service.delete(id)) {
                System.out.printf("O board %s foi excluido\n", id);
            } else {
                System.out.printf("Não foi encontrado um board com id %s\n", id);
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private BoardColumnEntity createColumn(final String name, final BoardColumnKindEnum kind, final int order) {
        BoardColumnEntity boardColumn = new BoardColumnEntity();
        boardColumn.setName(name);
        boardColumn.setKind(kind);
        boardColumn.setOrder(order);
        return boardColumn;
    }
}
