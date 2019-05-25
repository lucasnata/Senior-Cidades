package br.com.lucas.cidades.model.DTO;

import br.com.lucas.cidades.model.entity.City;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CityDTO {

    @JsonProperty("ibge_id")
    private Integer ibgeId;

    private String uf;

    private String name;

    private boolean capital;

    @JsonProperty("lon")
    private Double longitude;

    @JsonProperty("lat")
    private Double latitude;

    @JsonProperty("no_accents")
    private String noAccents;

    @JsonProperty("alternative_names")
    private String alternativeNames;

    private String microregion;

    private String mesoregion;

    public CityDTO() {
    }

    public CityDTO(Integer ibgeId, String uf, String name, boolean capital, Double longitude, Double latitude, String noAccents, String alternativeNames, String microregion, String mesoregion) {
        this.ibgeId = ibgeId;
        this.uf = uf;
        this.name = name;
        this.capital = capital;
        this.longitude = longitude;
        this.latitude = latitude;
        this.noAccents = noAccents;
        this.alternativeNames = alternativeNames;
        this.microregion = microregion;
        this.mesoregion = mesoregion;
    }

    public Integer getIbgeId() {
        return ibgeId;
    }

    public void setIbgeId(Integer ibgeId) {
        this.ibgeId = ibgeId;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getNoAccents() {
        return noAccents;
    }

    public void setNoAccents(String noAccents) {
        this.noAccents = noAccents;
    }

    public String getAlternativeNames() {
        return alternativeNames;
    }

    public void setAlternativeNames(String alternativeNames) {
        this.alternativeNames = alternativeNames;
    }

    public String getMicroregion() {
        return microregion;
    }

    public void setMicroregion(String microregion) {
        this.microregion = microregion;
    }

    public String getMesoregion() {
        return mesoregion;
    }

    public void setMesoregion(String mesoregion) {
        this.mesoregion = mesoregion;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "ibgeId=" + ibgeId +
                ", uf='" + uf + '\'' +
                ", name='" + name + '\'' +
                ", capital=" + capital +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", noAccents='" + noAccents + '\'' +
                ", alternativeNames='" + alternativeNames + '\'' +
                ", microregion='" + microregion + '\'' +
                ", mesoregion='" + mesoregion + '\'' +
                '}';
    }

    public static CityDTO convertCidadeToCidadeDTO(City city) {

        CityDTO cityDTO = new CityDTO(
                city.getIbgeId(),
                city.getUf(),
                city.getName(),
                city.isCapital(),
                city.getLongitude(),
                city.getLatitude(),
                city.getNoAccents(),
                city.getAlternativeNames(),
                city.getMicroregion(),
                city.getMesoregion()
        );

        return cityDTO;
    }

    public static List<CityDTO> convertListCidadeToListCidadeDTO(List<City> listCity) {

        List<CityDTO> listCityDTO = new ArrayList<>();

        listCity.forEach(city -> {
            listCityDTO.add(convertCidadeToCidadeDTO(city));
        });

        return listCityDTO;
    }

    public static City convertCidadeDTOToCidade(CityDTO cityDTO) {

        City city = new City(
                cityDTO.getIbgeId(),
                cityDTO.getUf(),
                cityDTO.getName(),
                cityDTO.isCapital(),
                cityDTO.getLongitude(),
                cityDTO.getLatitude(),
                cityDTO.getNoAccents(),
                cityDTO.getAlternativeNames(),
                cityDTO.getMicroregion(),
                cityDTO.getMesoregion()
        );

        return city;
    }

    public static List<City> convertListCidadeDTOToListCidade(List<CityDTO> listCityDTO) {

        List<City> listCity = new ArrayList<>();

        listCityDTO.forEach(city -> {
            listCity.add(convertCidadeDTOToCidade(city));
        });

        return listCity;
    }
}
