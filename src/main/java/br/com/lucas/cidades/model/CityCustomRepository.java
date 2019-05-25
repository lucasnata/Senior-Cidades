package br.com.lucas.cidades.model;

import org.springframework.stereotype.Repository;

@Repository
public interface CityCustomRepository{
    long countColumnName(String columnName);
}
