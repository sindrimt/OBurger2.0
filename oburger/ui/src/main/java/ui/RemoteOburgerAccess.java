package ui;

import java.io.IOError;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jackson.Receipt;
import jackson.ReceiptListResource;

public class RemoteOburgerAccess {
  private final URI endpointBaseUri;
  private final ObjectMapper mapper;

  public RemoteOburgerAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    mapper = new ObjectMapper();
  }

  public void postReceipt(Receipt receipt) {
    System.out.println("Trying to post receipt to server: "+receipt.getDescription());
    try {
      String json = mapper.writeValueAsString(receipt);
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("postreceipt"))
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofString(json))
        .build();
      final HttpResponse<String> response = 
        HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      Boolean added = mapper.readValue(responseString, Boolean.class);
      if (added != null) {
        System.out.println("Successfully posted receipt!");
      } else {
        System.out.println("Failed posting receipt (internal server error)");
      }
    } catch(IOException | InterruptedException e) {
      if (e instanceof ConnectException) {
        System.out.println("Failed posting receipt (couldn't contact server. Is it up and running?)");
        // Do not throw - this message appears often if the user forgets to start their server.
      }
      else {
        System.out.println("Failed posting receipt (an unknown error occured. If the problem persists, consult a deity)");
        throw new RuntimeException(e);
      }
    }
  }

  public List<Receipt> getReceipts() {
    ReceiptListResource resource;
    System.out.println("Trying to get receipts from server");
    HttpRequest request = HttpRequest.newBuilder(endpointBaseUri.resolve("getreceipts"))
        .header("Accept", "application/json")
        .GET()
        .build();
    try {
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      resource = mapper.readValue(response.body(), ReceiptListResource.class);
      List<Receipt> receipts = resource.getReceipts();
      System.out.println("Success! The server had "+receipts.size()+" receipts");
      return receipts;
    } catch (IOException | InterruptedException e) {
      if (e instanceof ConnectException) {
        System.out.println("Failed getting receipts (couldn't contact server. Is it up and running?)");
        // Do not throw - this message appears often if the user forgets to start their server
      }
      else {
        System.out.println("Failed getting receipts (an unknown error occured, some weird stuff is going on)");
        throw new RuntimeException(e);
      }
    }
    return new ArrayList<>();  // Error occured, display nothing
  }

  public static void main(String[] args) {
    try {
      URI uri = new URI("http://localhost:8080/postreceipt");
      Receipt r = new Receipt();
      List<String> names = new ArrayList<>();
      names.add("Example Food");
      List<Integer> counts = new ArrayList<>();
      counts.add(123);
      r.setItemNames(names);
      r.setCounts(counts);
      RemoteOburgerAccess access = new RemoteOburgerAccess(uri);
      access.postReceipt(r);
    } catch(Exception e) {
      throw new RuntimeException(e);
    }

  }
}
