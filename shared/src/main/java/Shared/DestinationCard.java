package Shared;

public enum DestinationCard {

    Denver_ElPaso(4),
    KansasCity_Houston(5),
    NewYork_Atlanta(6),
    Calgary_SaltLakeCity(7),
    Chicago_NewOrleans(7),
    Helena_LosAngeles(8),
    Duluth_Houston(8),
    SaultSteMarie_Nashville(9),
    Montreal_Atlanta(9) ,
    SaultSteMarie_OklahomaCity(9) ,
    Seattle_LosAngeles(9) ,
    Chicago_SantaFe(9) ,
    Duluth_ElPaso (10),
    Toronto_Miami(10),
    Portland_Phoenix (11),
    Dallas_NewYorkCity(11),
    Denver_Pittsburgh(11),
    Winnipeg_LittleRock(11),
    Winnipeg_Houston(12),
    Boston_Miami(12),
    Vancouver_SantaFe(13),
    Calgary_Phoenix (13),
    Montreal_NewOrleans (13),
    LosAngeles_Chicago(16),
    SanFrancisco_Atlanta (17),
    Portland_Nashville (17),
    Vancouver_Montreal(20),
    LosAngeles_Miami(20),
    LosAngeles_NewYorkCity(21),
    Seattle_NewYorkCity (22);

    public int points = 0;

    DestinationCard(Integer points_) {
        points = points_;
    }

    Integer showPoints() {
        return points;
    }
}

