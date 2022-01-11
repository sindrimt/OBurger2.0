package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Menu {
  // Using a HashMap allows for finding MenuItem with matching name in constant time
  private HashMap<String, MenuItem> items = new HashMap<>();

  public Menu(boolean useDefault) {
    if (useDefault) {
      addItem(new MenuItem("O'Burger", 10, "oburger.png"));
      addItem(new MenuItem("O'Shake", 10, "oshake.png"));
      addItem(new MenuItem("O'Fries", 10, "ofries.png"));
      addItem(new MenuItem("O'Nuggets", 10, "onuggets.png"));
    }
  }
  public void addItem(MenuItem item) {
    items.put(item.getName(), item);
  }
  public MenuItem getItem(String name) {
    if (name == null) throw new IllegalArgumentException("Tried getting item with name null");
    if (items.containsKey(name)) {
      return items.get(name);
    } else {
      throw new IllegalStateException("Menu does not contain name "+name);
    }
  }
  public List<MenuItem> getItems() {
    return new ArrayList<>(items.values());
  }
}
