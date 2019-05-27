package br.com.lucas.cidades.model.entity;

public class DistanciasCalculadas {

    private CidadeGPS cidadeGPS1;
    private CidadeGPS cidadeGPS2;
    private Double distancia;

    public DistanciasCalculadas() {
        this.setDistancia(0.0);
    }

    public DistanciasCalculadas(CidadeGPS cidadeGPS1, CidadeGPS cidadeGPS2, Double distancia) {
        this.cidadeGPS1 = cidadeGPS1;
        this.cidadeGPS2 = cidadeGPS2;
        this.distancia = distancia;
    }

    public CidadeGPS getCidadeGPS1() {
        return cidadeGPS1;
    }

    public void setCidadeGPS1(CidadeGPS cidadeGPS1) {
        this.cidadeGPS1 = cidadeGPS1;
    }

    public CidadeGPS getCidadeGPS2() {
        return cidadeGPS2;
    }

    public void setCidadeGPS2(CidadeGPS cidadeGPS2) {
        this.cidadeGPS2 = cidadeGPS2;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "DistanciasCalculadas{" +
                "cidadeGPS1=" + cidadeGPS1 +
                ", cidadeGPS2=" + cidadeGPS2 +
                ", distancia=" + distancia +
                '}';
    }
}
