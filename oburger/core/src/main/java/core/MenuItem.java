package core;

/**
 * MenuItems are foods that may be added to an Order.
 * MenuItems are immutable.
 */
public class MenuItem {
  private final String name;
  private final String imgFileName;
  private final double cost;

  /**
   * Creates a MenuItem with given parameters.
   * MenuItems are immutable.

   * @param name used for receipts and identity
   * @param cost is the cost per unit of this item
   * @param imgFileName file name to search for when displaying
   * @throws IllegalArgumentException if id or name is null, or cost is negative
   */
  public MenuItem(String name, double cost, String imgFileName) {
    if (imgFileName == null) {
      throw new IllegalArgumentException("Tried creating MenuItem with null imgFileName");
    }
    if (name == null) {
      throw new IllegalArgumentException("Tried creating MenuItem with null name");
    }
    if (cost < 0) {
      throw new IllegalArgumentException("Tried creating MenuItem with negative cost");
    }
    this.imgFileName = imgFileName;
    this.name = name;
    this.cost = cost;
  }

  public String getImgFileName() {
    return imgFileName;
  }

  public String getName() {
    return name;
  }

  public double getCost() {
    return cost;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
