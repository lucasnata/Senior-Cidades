package br.com.lucas.cidades.model;

public class FakeCity {

    public static City getFakeCity(){

        City city = new City(
                1100254,
                "RO",
                "Presidente Mdici",
                false,
                -61.9094386835,
                -11.1732050162,
                "Presidente Medici",
                "",
                "Ji-Paran",
                "Leste Rondoniense"
        );

        return city;

    }
}
