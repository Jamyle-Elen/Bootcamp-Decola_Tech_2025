package br.com.dio.dto;

import br.com.dio.persistence.entity.BoardColumnKindEnum;
import java.util.Objects;

public class BoardColumnDTO {
    private final Long id;
    private final String name;
    private final BoardColumnKindEnum kind;
    private final int cardsAmount;

    public BoardColumnDTO(Long id, String name, BoardColumnKindEnum kind, int cardsAmount) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.cardsAmount = cardsAmount;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public BoardColumnKindEnum kind() {
        return kind;
    }

    public int cardsAmount() {
        return cardsAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardColumnDTO that = (BoardColumnDTO) o;
        return cardsAmount == that.cardsAmount &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                kind == that.kind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, kind, cardsAmount);
    }

    @Override
    public String toString() {
        return "BoardColumnDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind=" + kind +
                ", cardsAmount=" + cardsAmount +
                '}';
    }
}
