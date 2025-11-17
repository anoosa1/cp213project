package cp213;

import java.util.List;

public class Recipe {
  // Variables
  protected String name;
  protected String description;
  int cookTimeMinutes;
  int servings;
  protected List<String> ingredients;
  protected List<String> steps;

  // The Constructor
  public Recipe(String name, String description, int cookTimeMinutes, int servings, List<String> ingredients, List<String> steps) {
    this.name = name;
    this.description = description;
    this.cookTimeMinutes = cookTimeMinutes;
    this.servings = servings;
    this.ingredients = ingredients;
    this.steps = steps;
  }

  // Getters
  public String getName() { return name; }
  public String getDescription() { return description; }
  public int getCookTimeMinutes() { return cookTimeMinutes; }
  public int getServings() { return servings; }
  public List<String> getIngredients() { return ingredients; }
  public List<String> getSteps() { return steps; }

  // Setters
  public void setName(String name) { this.name = name; }
  public void setDescription(String description) { this.description = description; }
  public void setCookTimeMinutes(int cookTimeMinutes) { this.cookTimeMinutes = cookTimeMinutes; }
  public void setServings(int servings) { this.servings = servings; }
  public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
  public void setSteps(List<String> steps) { this.steps = steps; }

  @Override
  public String toString() {
    return name + " (" + servings + " servings)";
  }
}
