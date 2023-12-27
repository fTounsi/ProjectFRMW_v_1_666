package ma.anizar.frmw.model.enums;

public enum StatusMatch {
  PROGRAMMED("Programmé"),
  IN_PROGRESS("En cours"),
  COMPLETED("Terminé");

  private final String label;

  StatusMatch(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
