import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try(BufferedReader reader = new BufferedReader(new FileReader("data/transactions.csv"))) {
            String line;
            reader.readLine(); // skips header
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //System.out.println(reader.readLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}