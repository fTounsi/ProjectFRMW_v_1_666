package ma.anizar.frmw.model.enums;

public enum TypeSanction {
    ADMONITION("Admonition"), // carte rouge
    WARNING("Warning"), // carte jaune
    FORCIBLE_COUNTING("Forcible Counting"), // KO 7sab
    OUT_BOX("OUT"); // kharej 7alaba

    private final String label;

    TypeSanction(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}