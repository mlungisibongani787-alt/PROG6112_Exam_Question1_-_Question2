package za.ac.iie.question2;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MattressSalesForm extends JFrame {
    private final JTextField customerNameField = new JTextField(22);
    private final JComboBox<String> productComboBox = new JComboBox<>(new String[]{
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"
    });
    private final JTextField priceField = new JTextField(22);

    public MattressSalesForm() {
        setTitle("Product App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());
        add(createForm(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(event -> dispose());
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(event -> processSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(event -> clearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    private JPanel createForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 24, 22, 24));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addRow(panel, gbc, 0, "Customer Name:", customerNameField);
        addRow(panel, gbc, 1, "Select Product:", productComboBox);
        addRow(panel, gbc, 2, "Enter Price:", priceField);

        JButton processButton = new JButton("PROCESS");
        processButton.addActionListener(event -> processSale());
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(processButton, gbc);
        return panel;
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, java.awt.Component field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private void processSale() {
        String customer = customerNameField.getText().trim();
        String product = (String) productComboBox.getSelectedItem();
        String priceText = priceField.getText().trim();

        if (customer.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name and product price.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return;
        }

        final double price;
        try {
            price = Double.parseDouble(priceText);
            if (!Double.isFinite(price) || price <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Enter a valid price greater than zero.",
                    "Invalid Price", JOptionPane.ERROR_MESSAGE);
            priceField.requestFocusInWindow();
            return;
        }

        ProductCalculations calculations = new ProductCalculations(price);
        String output = String.format(java.util.Locale.US,
                "Customer Name: %s%nProduct: %s%nVAT: R %.2f%nCOMMISSION: R %.2f%nTOTAL: R %.2f",
                customer, product, calculations.GetVat(), calculations.GetCommission(), calculations.GetTotal());
        try {
            saveSale(customer, product, price, calculations);
            JOptionPane.showMessageDialog(this, output, "Sales Details", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(this, "The sale could not be saved: " + exception.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveSale(String customer, String product, double price, ProductCalculations calculations)
            throws IOException {
        String record = String.format(java.util.Locale.US,
                "DATA LOG%n****************************************%nCustomer Name: %s%nProduct: %s%nPrice: R %.2f%nVAT: R %.2f%nCOMMISSION: R %.2f%nTOTAL: R %.2f%n****************************************%n%n",
                customer, product, price, calculations.GetVat(), calculations.GetCommission(), calculations.GetTotal());
        // Each processed sale is added to the end of the data file.
        Files.writeString(Path.of("data.txt"), record, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private void clearForm() {
        customerNameField.setText("");
        priceField.setText("");
        productComboBox.setSelectedIndex(0);
        customerNameField.requestFocusInWindow();
    }
}
