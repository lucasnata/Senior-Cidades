package br.com.lucas.cidades.model.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Distancies {

    @EmbeddedId
    DistancesId ibgeId;

    private Double longitude1;
    private Double latitude1;
    private Double longitude2;
    private Double latitude2;
    private Double distancia;

    public Distancies() {
    }

    public Distancies(DistancesId ibgeId, Double longitude1, Double latitude1, Double longitude2, Double latitude2, Double distancia) {
        this.ibgeId = ibgeId;
        this.longitude1 = longitude1;
        this.latitude1 = latitude1;
        this.longitude2 = longitude2;
        this.latitude2 = latitude2;
        this.distancia = distancia;
    }

    public DistancesId getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(DistancesId ibgeId) {
        this.ibgeId = ibgeId;
    }

    public Double getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(Double longitude1) {
        this.longitude1 = longitude1;
    }

    public Double getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(Double latitude1) {
        this.latitude1 = latitude1;
    }

    public Double getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(Double longitude2) {
        this.longitude2 = longitude2;
    }

    public Double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(Double latitude2) {
        this.latitude2 = latitude2;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Distancies{" +
                "ibgeId=" + ibgeId +
                ", longitude1=" + longitude1 +
                ", latitude1=" + latitude1 +
                ", longitude2=" + longitude2 +
                ", latitude2=" + latitude2 +
                ", distancia=" + distancia +
                '}';
    }
}
