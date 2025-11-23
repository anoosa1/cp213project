package cp213;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddRecipe extends JDialog {
  private Recipe recipe;
  private JButton addButton;
  private JButton cancelButton;
  private JTextField nameField;
  private JTextField descriptionField;
  private JTextField cookTimeField;
  private JTextField servingsField;
  private JTextField ingredientsField;
  private JTextField stepsField;

  public AddRecipe(JFrame parent) {
    super(parent, "Add Recipe", true);
    initializeComponents();
    layoutComponents();
    setupEventHandlers();
    pack();
    setLocationRelativeTo(parent);
  }

  private void initializeComponents() {
    addButton = new JButton("Add");
    cancelButton = new JButton("Cancel");
    nameField = new JTextField(20);
    descriptionField = new JTextField(20);
    cookTimeField = new JTextField(20);
    servingsField = new JTextField(20);
    ingredientsField = new JTextField(20);
    stepsField = new JTextField(20);
  }

  private void layoutComponents() {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;

    // Add fields
    gbc.gridx = 0; gbc.gridy = 0;
    mainPanel.add(new JLabel("Name:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(nameField, gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    mainPanel.add(new JLabel("Description:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(descriptionField, gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    mainPanel.add(new JLabel("Cook Time (minutes):"), gbc);
    gbc.gridx = 1;
    mainPanel.add(cookTimeField, gbc);

    gbc.gridx = 0; gbc.gridy = 3;
    mainPanel.add(new JLabel("Servings:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(servingsField, gbc);

    gbc.gridx = 0; gbc.gridy = 4;
    mainPanel.add(new JLabel("Ingredients (comma-separated):"), gbc);
    gbc.gridx = 1;
    mainPanel.add(ingredientsField, gbc);

    gbc.gridx = 0; gbc.gridy = 5;
    mainPanel.add(new JLabel("Steps (comma-separated):"), gbc);
    gbc.gridx = 1;
    mainPanel.add(stepsField, gbc);

    // Buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(cancelButton);
    buttonPanel.add(addButton);

    gbc.gridx = 0; gbc.gridy = 6;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.EAST;
    mainPanel.add(buttonPanel, gbc);

    add(mainPanel);
  }

  private void setupEventHandlers() {
    addButton.addActionListener(e -> {
      if (validateInput()) {
        createRecipe();
        dispose();
      }
    });

    cancelButton.addActionListener(e -> dispose());
  }

 private boolean validateInput() {
    if (nameField.getText().trim().isEmpty()) {
      JOptionPane.showMessageDialog(this, "Name cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    int cookTime;
    int servings;
    
    try {
      cookTime = Integer.parseInt(cookTimeField.getText().trim());
      servings = Integer.parseInt(servingsField.getText().trim());
    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Time and servings must be numbers", "Validation Error", JOptionPane.ERROR_MESSAGE);
      return false;
    }
    // Prevent negative or zero value
    if (cookTime <= 0) {
        JOptionPane.showMessageDialog(this, "Cook time must be a positive number", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    if (servings <= 0) {
        JOptionPane.showMessageDialog(this, "Servings must be a positive number", "Validation Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
  }
 

  private void createRecipe() {
    String name = nameField.getText().trim();
    String description = descriptionField.getText().trim();
    int cookTime = Integer.parseInt(cookTimeField.getText().trim());
    int servings = Integer.parseInt(servingsField.getText().trim());
    
    List<String> ingredients = parseListField(ingredientsField.getText());
    List<String> steps = parseListField(stepsField.getText());

    recipe = new Recipe(name, description, cookTime, servings, ingredients, steps);
  }

  private List<String> parseListField(String text) {
    if (text.trim().isEmpty()) return new ArrayList<>();
    return Arrays.stream(text.split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
  }

  public Recipe getRecipe() { return recipe; }
}
