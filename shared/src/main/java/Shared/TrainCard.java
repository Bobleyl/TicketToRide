package Shared;

public enum TrainCard {

    Locomotive ("wild"),

    Box ("pink"),

    Passenger ("white"),

    Tanker ("blue"),

    Reefer ("yellow"),

    Freight ("orange"),

    Hopper ("black"),

    Coal("red"),

    Caboose("green");

    public String color = "";

    TrainCard(String color_) {
        color = color_;
    }

    String showColor() {
        return color;
    }
}

