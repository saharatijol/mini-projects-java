import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                double amount = Double.parseDouble(arr[2]);
                transactionsList.add(new Transaction(arr[0], arr[1], amount));
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
        System.out.println("\n\nUsing the Categorizer Class");
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


        HashMap<String, Double> expenses = new HashMap<>();
        for(Transaction transaction : transactionsList) {
            String category = transaction.getCategory();
            Double amount = transaction.getAmount();
            Double total = expenses.getOrDefault(category, 0.0);

            total += amount;
            expenses.put(category, total);
        }

        System.out.println("\nTotal Spent: ");
        for (Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}