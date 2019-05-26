package br.com.lucas.cidades.model;

import br.com.lucas.cidades.model.DTO.MaisDistantes;
import br.com.lucas.cidades.model.entity.DistancesId;
import br.com.lucas.cidades.model.entity.Distancies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DistanceRepository extends CrudRepository<Distancies, DistancesId> {
    @Query(value = "select ibge_id1, ibge_id2 distancies order by disatancia desc limit 1", nativeQuery = true)
    MaisDistantes findMostDistant();
}