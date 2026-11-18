package za.ac.iie.question2;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MattressSalesForm().setVisible(true));
    }
}
