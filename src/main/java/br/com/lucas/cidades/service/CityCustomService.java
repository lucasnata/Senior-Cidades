package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.entity.City;
import br.com.lucas.cidades.model.CityCustomRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
public class CityCustomService implements CityCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countColumnName(String columnName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<City> root = query.from(City.class);

        query.select(criteriaBuilder.countDistinct(root.<String>get(columnName)));
        return entityManager.createQuery(query).getSingleResult().intValue();
    }
}
