package androidteam.cs340.tickettoride.Shared;

public enum Route {



    LosAngeles_SanFrancisco (Colors.yellow.showColor(), 3),

    LosAngeles_SanFrancisco_2 (Colors.pink.showColor(), 3) ,

    SanFrancisco_Portland ("green", 5) ,

    SanFrancisco_Portland_2 ("pink", 5) ,

    Portland_Seattle ("grey", 1) ,

    Portland_Seattle_2 ("grey", 1) ,

    Seattle_Vancouver ("grey", 1) ,

    Seattle_Vancouver_2 ("grey", 1) ,

    Vancouver_Calgary ("grey", 3) ,

    Calgary_Winnipeg ("white", 5) ,

    Winnipeg_SaultSteMarie ("grey", 5),

    SaultSteMarie_Montreal ("black", 5),

    Montreal_Boston ("grey", 2),

    Montreal_Boston_2 ("grey", 2),

    Boston_NewYork ("yellow", 2),

    Boston_NewYork_2 ("red", 2),

    NewYork_Washington ("black", 2) ,

    NewYork_Washington_2 ("orange", 2) ,

    Washington_Raleigh ("grey", 2) ,

    Washington_Raleigh_2 ("grey", 2) ,

    Raleigh_Charleston ("grey", 2) ,

    Charleston_Miami ("pink", 4) ,

    Miami_NewOrleans ("red", 6) ,

    NewOrleans_Houston ("grey", 2) ,

    Houston_ElPaso ("green", 6) ,

    ElPaso_LosAngeles ("black", 6) ,

    LosAngeles_LasVegas ("grey", 2) ,

    LosAngeles_Phoenix ("grey", 3) ,

    SanFrancisco_SaltLakeCity("white", 5) ,

    SanFrancisco_SaltLakeCity_2("orange", 5) ,

    Portland_SaltLakeCity ("blue", 6),

    Seattle_Calgary("grey", 4) ,

    Calgary_Hellena ("grey", 4),

    Winnipeg_Duluth ("black", 4),

    SaultSteMarie_Duluth("grey", 3) ,

    SaultSteMarie_Toronto("grey", 2) ,

    Montreal_Toronto("grey", 3) ,

    Montreal_NewYork ("blue", 3),

    NewYork_Pittsburgh("white", 2) ,

    NewYork_Pittsburgh_2("green", 2) ,

    Washington_Pittsburgh("grey", 2) ,

    Raleigh_Nashville("black", 3) ,

    Raleigh_Atlanta("grey", 2) ,

    Raleigh_Atlanta_2("grey", 2) ,

    Charleston_Atlanta("grey", 2) ,

    Miami_Atlanta("blue", 5) ,

    NewOrleans_Atlanta("yellow", 4) ,

    NewOrleans_Atlanta_2("orange", 4) ,

    NewOrleans_LittleRock("green", 3) ,

    Houston_Dallas("grey", 1) ,

    Houston_Dallas_2("grey", 1) ,

    ElPaso_Phoenix("grey", 3) ,

    ElPaso_SantaFe("grey", 2) ,

    ElPaso_OklahomaCity("yellow", 5) ,

    ElPaso_Dallas("red", 4) ,

    SaltLakeCity_LasVegas("orange", 3) ,

    SaltLakeCity_Denver("red", 3) ,

    SaltLakeCity_Denver_2("yellow", 3) ,

    SaltLakeCity_Hellena("pink", 3) ,

    Hellena_Duluth("orange", 6) ,

    Hellena_Denver ("green", 4) ,

    Hellena_Omaha ("red", 5) ,

    Duluth_Toronto("pink", 6) ,

    Duluth_Chicago("red", 3) ,

    Pittsburgh_Toronto("grey", 2) ,

    Pittsburgh_Chicago("orange", 3) ,

    Pittsburgh_Chicago_2("black", 3) ,

    Pittsburgh_Raleigh("grey", 2) ,

    Pittsburgh_Nashville("yellow", 4) ,

    Pittsburgh_SaintLouis("green", 5) ,

    Chicago_Toronto("white", 4) ,

    Chicago_Omaha("blue", 4) ,

    Chicago_SaintLouis("green", 2) ,

    Chicago_SaintLouis_2("white", 2) ,

    SaintLouis_Nashville("grey", 2) ,

    SaintLouis_KansasCity("pink", 2) ,

    SaintLouis_KansasCity_2("blue", 2) ,

    SaintLouis_LittleRock("grey", 2) ,

    KansasCity_Omaha("grey", 1) ,

    KansasCity_Omaha_2("grey", 1) ,

    KansasCity_Denver("black", 4) ,

    KansasCity_Denver_2("orange", 4),

    KansasCity_OklahomaCity("grey", 2),

    KansasCity_OklahomaCity_2("grey", 2),

    Denver_Phoenix("white", 5),

    Denver_SantaFe("grey", 2),

    Denver_Omaha("pink", 4),

    Denver_OklahomaCity("red", 4),

    Seattle_Hellena("yellow", 6),

    Winnipeg_Hellena("blue", 4),

    Duluth_Omaha("grey", 2),

    Duluth_Omaha_2("grey", 2),

    Nashville_Atlanta("grey", 1),

    Nashville_LittleRock("white", 3),

    LittleRock_Dallas("grey", 2),

    LittleRock_OklahomaCity("grey", 2),

    Dallas_OklahomaCity("grey", 2),

    Dallas_OklahomaCity_2("grey", 2),

    SantaFe_OklahomaCity("blue", 3),

    SantaFe_Phoenix("grey", 3);

    public int length = 0;
    public String color = "";

    Route(String color_, int length_) {
        length = length_;
        color = color_;
    }

    int showLength() {
        return length;
    }

    String showColor() {
        return color;
    }

}
