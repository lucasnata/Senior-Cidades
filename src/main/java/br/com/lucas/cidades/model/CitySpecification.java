package br.com.lucas.cidades.model;

import br.com.lucas.cidades.model.entity.City;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class CitySpecification implements Specification<City> {

    private String columnName;
    private String columnValue;

    public CitySpecification(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.<String>get(this.columnName), this.columnValue);
    }

}
