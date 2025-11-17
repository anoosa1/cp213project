package cp213;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ViewRecipe extends JDialog {
  private Recipe recipe;

  public ViewRecipe(JFrame parent, Recipe recipe) {
    super(parent, "View Recipe: " + recipe.getName(), true);
    this.recipe = recipe;
    layoutComponents();
    pack();
    setLocationRelativeTo(parent);
  }

  private void layoutComponents() {
    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // Name
    gbc.gridx = 0; gbc.gridy = 0;
    mainPanel.add(new JLabel("Name:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(createWrappedLabel(recipe.getName()), gbc);

    // Description
    gbc.gridx = 0; gbc.gridy = 1;
    mainPanel.add(new JLabel("Description:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(createWrappedLabel(recipe.getDescription()), gbc);

    // Cook Time
    gbc.gridx = 0; gbc.gridy = 2;
    mainPanel.add(new JLabel("Cook Time:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(new JLabel(recipe.getCookTimeMinutes() + " minutes"), gbc);

    // Servings
    gbc.gridx = 0; gbc.gridy = 3;
    mainPanel.add(new JLabel("Servings:"), gbc);
    gbc.gridx = 1;
    mainPanel.add(new JLabel(String.valueOf(recipe.getServings())), gbc);

    // Ingredients
    gbc.gridx = 0; gbc.gridy = 4;
    gbc.fill = GridBagConstraints.NONE;
    mainPanel.add(new JLabel("Ingredients:"), gbc);
    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1.0;
    gbc.weighty = 0.5;
    mainPanel.add(createListScrollPane(recipe.getIngredients()), gbc);

    // Steps
    gbc.gridx = 0; gbc.gridy = 5;
    gbc.fill = GridBagConstraints.NONE;
    gbc.weighty = 0.0;
    mainPanel.add(new JLabel("Steps:"), gbc);
    gbc.gridx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 0.5;
    mainPanel.add(createListScrollPane(recipe.getSteps()), gbc);

    // Close button
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());
    buttonPanel.add(closeButton);

    gbc.gridx = 0; gbc.gridy = 6;
    gbc.gridwidth = 2;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weighty = 0.0;
    gbc.anchor = GridBagConstraints.EAST;
    mainPanel.add(buttonPanel, gbc);

    add(mainPanel);
  }

  private JLabel createWrappedLabel(String text) {
    JLabel label = new JLabel("<html>" + text.replace("\n", "<br>") + "</html>");
    label.setPreferredSize(new Dimension(300, label.getPreferredSize().height));
    return label;
  }

  private JScrollPane createListScrollPane(List<String> items) {
    DefaultListModel<String> model = new DefaultListModel<>();
    for (String item : items) {
      model.addElement("• " + item);
    }
    JList<String> list = new JList<>(model);
    list.setVisibleRowCount(Math.min(5, items.size()));
    return new JScrollPane(list);
  }
}
