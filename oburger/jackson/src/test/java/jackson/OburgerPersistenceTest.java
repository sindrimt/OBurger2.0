package jackson;

import core.Order;
import core.MenuItem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OburgerPersistenceTest {
  private Order order;
  private OburgerPersistence persistence;
  private MenuItem fries;
  private Receipt receipt;

  @BeforeEach
  public void setup() {
    fries = new MenuItem("a", 10, "niceImg");
    order = new Order();
    order.addItem(fries, 1);
    order.finish();
    receipt = new Receipt(order);
  }

  /* With non-empty constructor
  @Test
  public void constructorTest() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {new ReceiptSerializer(null); });
    new ReceiptSerializer(receipt);
  }
  */

  @Test
  public void receiptToJsonTest() {
    OburgerPersistence persistence = new OburgerPersistence();
    persistence.saveReceiptToFile(receipt);
  }
}
