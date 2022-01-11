package jackson;

import java.util.ArrayList;
import java.util.List;

public class ReceiptListResource {
  private List<Receipt> receipts;

  public ReceiptListResource(List<Receipt> receipts) {
    this.receipts = receipts;
  }

  public ReceiptListResource() {
    // Empty constructor necessary for deserialization
  }
  public void setReceipts(List<Receipt> receipts) {
    // Used when deserializing
    this.receipts = receipts;
  }

  public List<Receipt> getReceipts() {
    return new ArrayList<>(receipts);
  }
}
