package br.com.lucas.cidades.model.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DistancesId implements Serializable {
    private Integer ibgeId1;
    private Integer ibgeId2;

    public DistancesId() {
    }

    public DistancesId(Integer ibgeId1, Integer ibgeId2) {
        this.ibgeId1 = ibgeId1;
        this.ibgeId2 = ibgeId2;
    }
}
