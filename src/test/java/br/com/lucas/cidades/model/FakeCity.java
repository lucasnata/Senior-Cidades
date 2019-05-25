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

    public static City getFakeCapitalCity(){

        City city = new City(
                1600303,
                "AP",
                "Macap",
                true,
                -51.057405457,
                0.03895101,
                "Macapa",
                "",
                "Macap",
                "Sul do Amap");

        return city;

    }
}
