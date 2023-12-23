package ma.anizar.frmw.model.enums;

public enum TypeSanction {
    ADMONITION("Admonition"),
    WARNING("Warning"),
    FORCIBLE_COUNTING("Forcible Counting"),
    APPEAL("Appeal");

    private final String label;

    TypeSanction(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}