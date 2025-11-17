package cp213;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu extends JFrame {
  private DefaultListModel<Recipe> recipeListModel;
  private JList<Recipe> recipeJList;
  private JButton removeButton;

  public MainMenu() {
    super("ByteSize Recipes");
    initializeComponents();
    layoutComponents();
    setupEventHandlers();
  }

  private void initializeComponents() {
    recipeListModel = new DefaultListModel<>();
    recipeJList = new JList<>(recipeListModel);
    recipeJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    recipeJList.setCellRenderer(new RecipeListCellRenderer());
  }

  private void layoutComponents() {
    setLayout(new BorderLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 500);
    setLocationRelativeTo(null);

    // Header Panel
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
    headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JLabel titleLabel = new JLabel("ByteSize Recipes");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

    // Buttons Panel (Remove left, Add right)
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
    removeButton = new JButton("Remove");
    JButton addButton = new JButton("Add");
    
    buttonPanel.add(removeButton);
    buttonPanel.add(addButton);

    headerPanel.add(titleLabel);
    headerPanel.add(Box.createHorizontalGlue());
    headerPanel.add(buttonPanel);

    add(headerPanel, BorderLayout.NORTH);

    // Recipe List
    JScrollPane scrollPane = new JScrollPane(recipeJList);
    scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
    add(scrollPane, BorderLayout.CENTER);
  }

  private void setupEventHandlers() {
    // Get references to buttons from headerPanel
    JPanel headerPanel = (JPanel) getContentPane().getComponent(0);
    JPanel buttonPanel = (JPanel) headerPanel.getComponent(2);
    JButton addButton = (JButton) buttonPanel.getComponent(1); // Remove is index 0, Add is index 1

    // Add button action
    addButton.addActionListener(e -> {
      AddRecipe dialog = new AddRecipe(this);
      dialog.setVisible(true);
      
      Recipe newRecipe = dialog.getRecipe();
      if (newRecipe != null) {
        recipeListModel.addElement(newRecipe);
      }
    });

    // Remove button action (keep as instance variable)
    removeButton.addActionListener(e -> {
      int selectedIndex = recipeJList.getSelectedIndex();
      if (selectedIndex != -1) {
        recipeListModel.remove(selectedIndex);
      }
    });

    // Enable/disable remove button based on selection
    recipeJList.addListSelectionListener(e -> {
      if (!e.getValueIsAdjusting()) {
        removeButton.setEnabled(recipeJList.getSelectedIndex() != -1);
      }
    });

    // Initially disable remove button
    removeButton.setEnabled(false);
  }

  public void addRecipe(Recipe recipe) {
    recipeListModel.addElement(recipe);
  }

  private static class RecipeListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
      JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      Recipe recipe = (Recipe) value;
      label.setText(recipe.getName() + " (" + recipe.getServings() + " servings)");
      label.setBorder(new EmptyBorder(5, 10, 5, 10));
      return label;
    }
  }
}
