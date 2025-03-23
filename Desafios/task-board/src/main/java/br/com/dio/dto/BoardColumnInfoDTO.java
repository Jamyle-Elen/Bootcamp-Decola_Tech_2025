package br.com.dio.dto;

import br.com.dio.persistence.entity.BoardColumnKindEnum;
import java.util.Objects;

public class BoardColumnInfoDTO {
    private final Long id;
    private final int order;
    private final BoardColumnKindEnum kind;

    public BoardColumnInfoDTO(Long id, int order, BoardColumnKindEnum kind) {
        this.id = id;
        this.order = order;
        this.kind = kind;
    }

    public Long id() {
        return id;
    }

    public int order() {
        return order;
    }

    public BoardColumnKindEnum kind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardColumnInfoDTO that = (BoardColumnInfoDTO) o;
        return order == that.order &&
                Objects.equals(id, that.id) &&
                kind == that.kind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, kind);
    }

    @Override
    public String toString() {
        return "BoardColumnInfoDTO{" +
                "id=" + id +
                ", order=" + order +
                ", kind=" + kind +
                '}';
    }
}
