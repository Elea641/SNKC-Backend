package projet.wcs.starter.models.enums;

public enum StateOfWearType {
    NEUF ("Neuf"),
    TRES_BON_ETAT ("Très bon état"),
    BON_ETAT ("Bon état"),
    ETAT_MOYEN ("Etat moyen"),
    USE ("usé");

    private final String name;

    StateOfWearType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
