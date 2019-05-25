package br.com.lucas.cidades.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {
    Iterable<City> findByCapitalTrueOrderByName();

    @Query(value = "SELECT COUNT(ibge_id) as numCidades, uf FROM cidade GROUP BY uf ORDER BY 1 DESC LIMIT 1", nativeQuery = true)
    NumberCitiesState findUfMaxCidades();

    @Query(value = "SELECT COUNT(ibge_id) as numCidades, uf FROM cidade GROUP BY uf ORDER BY 1 LIMIT 1", nativeQuery = true)
    NumberCitiesState findUfMinCidades();

    @Query(value = "SELECT COUNT(ibge_id) as numCidades, uf FROM cidade GROUP BY uf ORDER BY 1 ", nativeQuery = true)
    Iterable<NumberCitiesState> countByUf();

    City findByIbgeId(Integer ibgeId);

    Iterable<CityName> findNameByUf(String uf);

    long count();
}

