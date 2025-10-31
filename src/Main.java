import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static final String FILE = "data/transactions.csv";

    public static void main(String[] args) {

        ArrayList<Transaction> transactionsList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {

            String line;
            reader.readLine(); // skips header
            while((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                LocalDate date = LocalDate.parse(arr[0]);
                BigDecimal amount = new BigDecimal(arr[2]);
                transactionsList.add(new Transaction(date, arr[1], amount));
            }

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + FILE + " not found in data/ folder");

        } catch (IOException e) {
            System.err.println("ERROR: Could not read file - " + e.getMessage());
            e.printStackTrace();
        }

        Categorizer categorizer = new Categorizer();
        for(Transaction transaction : transactionsList) {
            categorizer.categorize(transaction);
        }

        ReportGenerator reportGenerator = new ReportGenerator(transactionsList);
        reportGenerator.printToConsole();
    }
}