package br.com.dio.dto;

import java.util.Objects;

public class CardDetailsDTO {
    private final Long id;
    private final String title;
    private final String description;
    private final boolean blocked;
    private final String blockReason;
    private final int blocksAmount;
    private final Long columnId;
    private final String columnName;

    public CardDetailsDTO(Long id, String title, String description, boolean blocked,
            String blockReason, int blocksAmount, Long columnId, String columnName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.blocked = blocked;
        this.blockReason = blockReason;
        this.blocksAmount = blocksAmount;
        this.columnId = columnId;
        this.columnName = columnName;
    }

    public Long id() {
        return id;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public boolean blocked() {
        return blocked;
    }

    public String blockReason() {
        return blockReason;
    }

    public int blocksAmount() {
        return blocksAmount;
    }

    public Long columnId() {
        return columnId;
    }

    public String columnName() {
        return columnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CardDetailsDTO that = (CardDetailsDTO) o;
        return blocked == that.blocked &&
                blocksAmount == that.blocksAmount &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(blockReason, that.blockReason) &&
                Objects.equals(columnId, that.columnId) &&
                Objects.equals(columnName, that.columnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, blocked, blockReason, blocksAmount,
                columnId, columnName);
    }

    @Override
    public String toString() {
        return "CardDetailsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", blocked=" + blocked +
                ", blockReason='" + blockReason + '\'' +
                ", blocksAmount=" + blocksAmount +
                ", columnId=" + columnId +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
