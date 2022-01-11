package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuItemTest {
  private MenuItem testItem;

  @BeforeEach
  public void prepare() {
    testItem = new MenuItem("niceName", 404, "niceImg");
  }

  @Test
  public void testConstructor() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new MenuItem(null, 10, "a");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new MenuItem("a", 10, null);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new MenuItem("a", -1, "a");
    });
    new MenuItem("a", 10, "a");
  }

  @Test
  public void testGetImgFileName() {
    Assertions.assertTrue(testItem.getImgFileName().equals("niceImg"));
  }
  @Test
  public void testGetName() {
    Assertions.assertTrue(testItem.getName().equals("niceName"));
  }
  @Test
  public void testGetCost() {
    Assertions.assertTrue(testItem.getCost() == 404);
  }
  @Test
  public void testToString() {
    Assertions.assertTrue(testItem.toString().equals(testItem.getName()));
  }
}
