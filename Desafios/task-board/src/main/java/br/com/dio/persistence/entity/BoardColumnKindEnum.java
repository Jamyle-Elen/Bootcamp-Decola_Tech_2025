package br.com.dio.persistence.entity;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

public enum BoardColumnKindEnum {

INITIAL, FINAL, CANCEL, PENDING;

public static BoardColumnKindEnum findByName(final String name){
    return Stream.of(BoardColumnKindEnum.values())
            .filter(b -> b.name().equals(name))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("BoardColumnKindEnum não encontrado para o nome: " + name));
}

}
