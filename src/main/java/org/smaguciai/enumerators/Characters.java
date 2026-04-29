package org.smaguciai.enumerators;




public enum Characters {
    LIŪTUKAS("Liūtukas"),
    STITČAS("Stitčas"),
    VIENARAGĖ("Vienaragė"),
    DRUGELIS("Drugelis"),
    LENKTYNININKĖ("Lenkty-<br>nininkė"),
    FĖJA("Fėja"),
    TREČIADIENĖ("Trečia-<br>dienė"),
    ELZA("Elza"),
    KLOUNIUKĖ("Klouniukė"),
    VAJANA("Vajana"),
    MIKĖ("Mikė"),
    RAGANIUKĖ("Raganiukė"),
    DŽIUNGLIŲ_MERGAITĖ("Džiunglių<br>mergaitė"),
    KAKĖ_MAKĖ("Kakė<br>makė"),
    NETVARKOS_NYKŠTUKAS("Netvarkos<br>nykštukas"),
    BITĖ_MAJA("Bitė<br>maja"),
    BARBĖ("Barbė"),
    INDĖNĖ("Indėnė"),
    PIMPAČKIUKAS("Pimpačkiukas"),
    UNDINĖLĖ("Undinėlė"),
    PIRATĖ("Piratė"),
    POLICININKĖ("Policininkė"),
    ŠUNYTĖ_SKYE("Šunytė<br>Skye"),
    KIŠKUTĖ("Kiškutė"),
    ASTRONAUTĖ("Astronautė"),
    ŽMOGUS_VORAS("Žmogus<br>voras"),
    MEDUOLIUKĖ("Meduo-<br>liukė");

    private final String label;
    Characters(String label){
        this.label=label;
    }
    public String getLabel(){

        return label;
    }
}
