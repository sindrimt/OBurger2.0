package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModelTest {
  // Tests for Logic.java
  private Model model;
  private Model notStartedOrder;
  MenuItem testItem;

  @BeforeEach
  public void prepare() {
    notStartedOrder = new Model();
    model = new Model();
    model.beginOrder();
    testItem = new MenuItem("abc", 100, "def");
    model.getMenu().addItem(new MenuItem("existingName", 10, "existingImg"));
  }


  @Test
  public void testFinishOrder() {
    Assertions.assertThrows(IllegalStateException.class, () -> {
      notStartedOrder.finishOrder();
    });
    model.finishOrder();
  }

  @Test
  public void testGetOrder() {
    Assertions.assertTrue(model.getOrder() != null);
    Assertions.assertTrue(notStartedOrder.getOrder() == null);
  }

  @Test
  public void addItemTest() {
    model.addItem(testItem, 1);
    model.addItem(testItem, 5);
    Assertions.assertEquals(6, model.getOrder().getCount(testItem));
    Assertions.assertThrows(IllegalStateException.class, () -> {
      notStartedOrder.addItem(testItem, 1);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      model.addItem(null, 1);
    });
  }

  @Test
  public void removeItemTest() {
    Assertions.assertEquals(0, model.getOrder().getCount(testItem));
    model.addItem(testItem, 1);
    Assertions.assertEquals(1, model.getOrder().getCount(testItem));
    model.removeItem(testItem, 2);
    Assertions.assertEquals(0, model.getOrder().getCount(testItem));
    model.removeItem(testItem, 1);
    Assertions.assertEquals(0, model.getOrder().getCount(testItem));

    Assertions.assertThrows(IllegalStateException.class, () -> {
      notStartedOrder.removeItem(testItem, 1);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      model.removeItem(null, 1);
    });
  }

  @Test
  public void testPageSwitch() {

    notStartedOrder.beginOrder();
    notStartedOrder.beginOrder();  // should not throw
  }

  @Test
  public void testGetMenuItem() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      model.getMenuItem(null);
    });
    Assertions.assertThrows(IllegalStateException.class, () -> {
      model.getMenuItem("nonexistentName");
    });
    Assertions.assertFalse(model.getMenuItem("existingName") == null);
  }

  @Test
  public void testGetMenu() {
    Assertions.assertTrue(model.getMenu() != null);
  }
}
