package cp213;

import javax.swing.SwingUtilities;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      MainMenu app = new MainMenu();
      app.setVisible(true);
    });
  }
}
