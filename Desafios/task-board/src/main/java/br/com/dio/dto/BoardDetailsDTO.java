package br.com.dio.dto;

import java.util.List;
import java.util.Objects;

public class BoardDetailsDTO {
    private final Long id;
    private final String name;
    private final List<BoardColumnDTO> columns;

    public BoardDetailsDTO(Long id, String name, List<BoardColumnDTO> columns) {
        this.id = id;
        this.name = name;
        this.columns = columns;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<BoardColumnDTO> columns() {
        return columns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardDetailsDTO that = (BoardDetailsDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(columns, that.columns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, columns);
    }

    @Override
    public String toString() {
        return "BoardDetailsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
