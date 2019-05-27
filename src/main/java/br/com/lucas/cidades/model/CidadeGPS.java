package br.com.lucas.cidades.model;

public class CidadeGPS {

    private Integer ibgeId;
    private Double latitude;
    private Double longitude;

    public CidadeGPS() {
    }

    public CidadeGPS(Integer ibgeId, Double latitude, Double longitude) {
        this.ibgeId = ibgeId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(Integer ibgeId) {
        this.ibgeId = ibgeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "CidadeGPS{" +
                "ibgeId=" + ibgeId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
