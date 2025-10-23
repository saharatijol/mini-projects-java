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


        // Print Transaction objects
        for(Transaction transaction : transactionsList) {
            System.out.println(transaction.getDate() + "     "
                    + transaction.getDescription() + "     "
                    + transaction.getAmount());
        }

        // Categorize Expenses
        // keywords to find and categorize
        System.out.println("\nCategorize Items:");
        System.out.println("Expense Breakdown:");
        ArrayList<Transaction> groceries = new ArrayList<>();
        ArrayList<Transaction> gasList = new ArrayList<>();
        ArrayList<Transaction> entertainList = new ArrayList<>();
        String description = "";

        // Groceries
        System.out.println("Groceries:");
        for(Transaction transaction : transactionsList) {
            description = transaction.getDescription();
            if(description.contains("Grocery") || description.contains("Market")) {
                groceries.add(transaction);
                System.out.println(description + "     " + transaction.getAmount());
            }
        }

        // Gas
        System.out.println("\nGas:");
        for(Transaction transaction : transactionsList) {
            description = transaction.getDescription();
            if(description.contains("Gas")) {
                gasList.add(transaction);
                System.out.println(description + "     " + transaction.getAmount());
            }
        }

        // Entertainment
        System.out.println("\nEntertainment:");
        for(Transaction transaction : transactionsList) {
            description = transaction.getDescription();
            if(description.contains("Netflix") || description.contains("HBO") ||
                    (description.contains("Spotify") || description.contains("Steam"))) {
                entertainList.add(transaction);
                System.out.println(description + "     " + transaction.getAmount());
            }
        }


        HashMap<String, Double> expenses = new HashMap<>();
        double groceryTotal = 0;
        double gasTotal = 0;
        double entTotal = 0;

        for(Transaction grocery : groceries) {
            groceryTotal += grocery.getAmount();
        }

        for(Transaction gas : gasList) {
            gasTotal += gas.getAmount();
        }

        for(Transaction entertainment : entertainList) {
            entTotal += entertainment.getAmount();
        }


        expenses.put("Grocery", groceryTotal);
        expenses.put("Gas", gasTotal);
        expenses.put("Entertainment", entTotal);

        System.out.println("\nTotal Spent:");
        for(Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


    }
}