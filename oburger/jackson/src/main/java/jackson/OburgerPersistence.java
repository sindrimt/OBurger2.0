package jackson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;

public class OburgerPersistence {
  private static final String DEFAULT_FILE_NAME = "oburger-receipts.json";
  private final ObjectMapper mapper = new ObjectMapper();
  private String fileName = DEFAULT_FILE_NAME;

    public void setFileName(String fileName) {
      if (fileName == null) {
        throw new IllegalArgumentException("Tried setting Persistence file name to null");
      }
      if (!fileName.endsWith(".json")) {
        throw new IllegalArgumentException("Tried setting Persistence file name to "+fileName+", which doesn't end with .json");
      }
    }

    public boolean saveReceiptToFile(Receipt receipt) {
        // Deserializes the file into a list of Receipts, which means saving receipts
        // will have relatively poor performance.
        // Doing this because closed json files can't be written to without risking poor
        // formatting
        // (For our fellow algdaters:)
        // The time complexity is something like Θ(n) where n is the amount of saved receipts

        List<Receipt> previousReceipts = getReceipts();
        File file = getOrCreateFile();
        
        try {
          Writer writer = new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8");
          SequenceWriter seqWriter = mapper.writer().writeValuesAsArray(writer);
          seqWriter.write(receipt); // Serialize the new receipt, newest end up first in file
          for (Receipt r : previousReceipts) {
            seqWriter.write(r); // Re-serialize the previously deserialized receipts
          }
          seqWriter.close();
          return true;
          
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("What the fuck");
          throw new RuntimeException(e);
        }
      }

    public List<Receipt> getReceipts() {
        File file = getOrCreateFile();
        
        try {
          return mapper.readValue(file, new TypeReference<List<Receipt>>() {});
        } catch(Exception e) {
          e.printStackTrace();
          return new ArrayList<>();
        }
        
      }
    
      /**
       * Using the fileName field:
       * finds and returns the file,
       * or creates it if nonexistent.
       
       * @throws Exception if something went wrong
       * @return the found/generated file
       */
      private File getOrCreateFile() {
        Path path = Paths.get(System.getProperty("user.home"), fileName);
        try {
          File file = path.toFile();
          if (file.createNewFile()) {
            System.out.println("Couldn't find the file '"+path.toString()+"', creating a new file at that location");
          }
          return file;
        }
        catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
}
