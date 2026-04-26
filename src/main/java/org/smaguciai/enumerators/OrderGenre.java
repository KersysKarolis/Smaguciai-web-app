package org.smaguciai.enumerators;

public enum OrderGenre {
    KRIKŠTYNOS("Krikštynos"),
    GIMTADIENIS("Gimtadienis"),
    VESTUVĖS("Vestuvės"),
    JUBILIEJUS("Jubiliejus"),
    IMONĖS_ŠVENTĖ("Įmonės šventė"),
    KITA("Kita");

    private final String label;
    OrderGenre(String label){
        this.label=label;
    }
    public String getLabel(){
        return label;
    }

}
