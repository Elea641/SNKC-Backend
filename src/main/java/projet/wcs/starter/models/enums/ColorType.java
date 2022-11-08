package projet.wcs.starter.models.enums;

public enum ColorType {
    WHITE ("Blanc"),
    BLACK ("Noir"),
    RED ("Rouge"),
    BLUE ("Bleu"),
    GREEN ("Vert"),
    PURPLE ("Violet"),
    YELLOW ("Jaune"),
    ORANGE ("Orange"),
    PINK ("Rose"),
    BROWN ("Marron"),
    GREY ("Gris"),
    MULTI ("Multicouleur"),
    OTHER ("Autre");

    private final String name;

    ColorType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
