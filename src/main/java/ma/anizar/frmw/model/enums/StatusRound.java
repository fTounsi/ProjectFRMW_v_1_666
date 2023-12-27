package ma.anizar.frmw.model.enums;

public enum StatusRound {
  PROGRAMMED("Programmé"),
  IN_PROGRESS("En cours"),
  COMPLETED("Terminé");

  private final String label;

  StatusRound(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
