package Shared;

public enum DestinationCard {

    Denver_ElPaso(4, "Denver", "El Paso"),
    KansasCity_Houston(5, "Kansas City", "Houston"),
    NewYork_Atlanta(6, "New York", "Atlanta"),
    Calgary_SaltLakeCity(7, "Calgary", "Salt Lake City"),
    Chicago_NewOrleans(7, "Chicago", "New Orleans"),
    Helena_LosAngeles(8, "Helena", "Los Angeles"),
    Duluth_Houston(8, "Duluth", "Houston"),
    SaultSteMarie_Nashville(9, "Sault Ste Marie", "Nashville"),
    Montreal_Atlanta(9, "Montreal", "Atlanta") ,
    SaultSteMarie_OklahomaCity(9, "Sault Ste Marie", "Oklahoma City") ,
    Seattle_LosAngeles(9, "Seattle", "Los Angeles") ,
    Chicago_SantaFe(9, "Chicago", "Santa Fe") ,
    Duluth_ElPaso (10,"Duluth","El Paso"),
    Toronto_Miami(10,"Toronto","Miami"),
    Portland_Phoenix (11,"Portland","Phoenix"),
    Dallas_NewYorkCity(11,"Dallas","New York City"),
    Denver_Pittsburgh(11,"Denver","Pittsburgh"),
    Winnipeg_LittleRock(11,"Winnipeg","Little Rock"),
    Winnipeg_Houston(12,"Winnipeg","Houston"),
    Boston_Miami(12,"Boston","Miami"),
    Vancouver_SantaFe(13,"Vancouver","Santa Fe"),
    Calgary_Phoenix (13,"Calgary","Phoenix"),
    Montreal_NewOrleans (13,"Montreal","New Orleans"),
    LosAngeles_Chicago(16,"Los Angeles","Chicago"),
    SanFrancisco_Atlanta (17,"San Francisco","Atlanta"),
    Portland_Nashville (17,"Portland","Nashville"),
    Vancouver_Montreal(20,"Vancouver","Montreal"),
    LosAngeles_Miami(20,"Los Angeles","Miami"),
    LosAngeles_NewYorkCity(21,"Los Angeles","New York City"),
    Seattle_NewYorkCity (22,"Seattle","New York City");

    public int points;
    public String cityA;
    public String cityB;

    DestinationCard(Integer points_, String a_, String b_) {
        points = points_;
        cityA = a_;
        cityB = b_;
    }

    Integer showPoints() {
        return points;
    }

    String getCityA(){
        return cityA;
    }

    String getCityB(){
        return cityB;
    }
}



