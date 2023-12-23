package ma.anizar.frmw.model.enums;

public enum StatusMatch {
    PROGRAMME("Programmé"),
    EN_COURS("En cours"),
    TERMINE("Terminé");

    private final String label;

    StatusMatch(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}