import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static final String FILE = "data/transactions.csv";

    public static void main(String[] args) {

        ArrayList<Transaction> transactionsList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {

            String line;
            reader.readLine(); // skips header
            while((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                LocalDate parsedDateFormat = LocalDate.parse(arr[0]);
                BigDecimal amount = new BigDecimal(arr[2]);
                transactionsList.add(new Transaction(parsedDateFormat, arr[1], amount));
            }

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + FILE + " not found in data/ folder");

        } catch (IOException e) {
            System.err.println("ERROR: Could not read file - " + e.getMessage());
            e.printStackTrace();
        }


        System.out.println("\nTransaction WITHOUT Categories");
        for(Transaction transaction : transactionsList) {
            System.out.println(transaction.getDate() + "     "
                    + transaction.getDescription() + "     "
                    + transaction.getAmount());
        }


        Categorizer categorizer = new Categorizer();
        for(Transaction transaction : transactionsList) {
            categorizer.categorize(transaction);
        }


        System.out.println("\nTransactions WITH Category: ");
        for(Transaction transaction : transactionsList) {
            System.out.println(transaction.getDate() + "     "
                    + transaction.getDescription() + "     "
                    + transaction.getAmount() + "     "
                    + transaction.getCategory());
        }


        HashMap<String, BigDecimal> expenses = new HashMap<>();
        for(Transaction transaction : transactionsList) {
            String category = transaction.getCategory();
            BigDecimal amount = transaction.getAmount();
            BigDecimal total = expenses.getOrDefault(category, BigDecimal.ZERO);

            total = total.add(amount);
            expenses.put(category, total);
        }

        System.out.println("\nTotal Spent: ");
        for (Map.Entry<String, BigDecimal> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}