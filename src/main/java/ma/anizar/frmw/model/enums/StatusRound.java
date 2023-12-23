package ma.anizar.frmw.model.enums;

public enum StatusRound {
    PROGRAMME("Programmé"),
    EN_COURS("En cours"),
    TERMINE("Terminé");

    private final String label;

    StatusRound(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}