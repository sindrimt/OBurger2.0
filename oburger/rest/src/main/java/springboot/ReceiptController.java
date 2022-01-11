package springboot;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jackson.Receipt;
import jackson.ReceiptListResource;
import jackson.OburgerPersistence;

@RestController
public class ReceiptController {
  public static final String GET_PATH = "getreceipts";
  public static final String POST_PATH = "postreceipt";
  private final OburgerPersistence persistence = new OburgerPersistence();

  @GetMapping("/test")
  public String test() {
    return "HEY";
  }

  @GetMapping("/"+GET_PATH)
  public ReceiptListResource getReceipts() {
    System.out.println("\n-----------------------------\n");
    System.out.println("Someone requested receipts!");
    
    List<Receipt> receipts = persistence.getReceipts();
    ReceiptListResource resource = new ReceiptListResource(receipts);

    if (receipts == null) {
      System.out.println("FAILED GETTING RECEIPTS; INTERNAL SERVER ERROR");
    } else {
      System.out.println("SUCCESSFULLY RETURNED "+receipts.size()+" RECEIPTS");
    }
    System.out.println("\n-----------------------------\n");
    return resource;
  }

  @PostMapping("/"+POST_PATH)
  public boolean postReceipt(@RequestBody Receipt receipt) {
    System.out.println("\n-----------------------------\n");
    System.out.println("Someone tried posting a receipt!");
    System.out.println("Receipt: "+receipt.getDescription());
    
    boolean added = persistence.saveReceiptToFile(receipt);
    if (added) {
      System.out.println("SUCCESSFULLY ADDED RECEIPT");
    } else {
      System.out.println("FAILED ADDING RECEIPT; INTERNAL SERVER ERROR");
    }
    System.out.println("\n-----------------------------\n");
    return added;
  }
}
