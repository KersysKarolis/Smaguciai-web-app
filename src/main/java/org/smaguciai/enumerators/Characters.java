package org.smaguciai.enumerators;




public enum Characters {
    LIŪTUKAS("Liūtukas"),
    STITČAS("Stitčas"),
    VIENARAGĖ("Vienaragė"),
    DRUGELIS("Drugelis"),
    LENKTYNININKĖ("Lenktynininkė"),
    FĖJA("Fėja"),
    TREČIADIENĖ("Trečiadienė"),
    ELZA("Elza"),
    KLOUNIUKĖ("Klouniukė"),
    VAJANA("Vajana"),
    MIKĖ("Mikė"),
    RAGANIUKĖ("Raganiukė"),
    DŽIUNGLIŲ_MERGAITĖ("Džiunglių mergaitė"),
    KAKĖ_MAKĖ("Kakė makė"),
    NETVARKOS_NYKŠTUKAS("Netvarkos nykštukas"),
    BITĖ_MAJA("Bitė maja"),
    BARBĖ("Barbė"),
    INDĖNĖ("Indėnė"),
    PIMPAČKIUKAS("Pimpačkiukas"),
    UNDINĖLĖ("Undinėlė"),
    PIRATĖ("Piratė"),
    POLICININKĖ("Policininkė"),
    ŠUNYTĖ_SKYE("Šunytė Skye"),
    KIŠKUTĖ("Kiškutė"),
    ASTRONAUTĖ("Astronautė"),
    ŽMOGUS_VORAS("Žmogus voras"),
    ANGELA("Angela"),
    RUMI("Rumi"),
    MEDUOLIUKĖ("Meduoliukė");

    private final String label;
    Characters(String label){
        this.label=label;
    }
    public String getLabel(){

        return label;
    }
}
