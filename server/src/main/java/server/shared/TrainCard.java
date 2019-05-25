package server.shared;

public enum TrainCard {

    Locomotive {
        public String color = "wild";
    },
    Box {
        public String color = "pink";
    },
    Passenger {
        public String color = "white";
    },
    Tanker {
        public String color = "blue";
    },
    Reefer {
        public String color = "yellow";
    },
    Freight {
        public String color = "orange";
    },
    Hopper {
        public String color = "black";
    },
    Coal {
        public String color = "red";
    },
    Caboose {
        public String color = "green";
    };

    public String color = "";
}
