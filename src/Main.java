import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


        // i can do the totals here according to categories (it works but ....)
        // but there is repeating logic with this
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


        // display total expenses per category
        expenses.put("Grocery", groceryTotal);
        expenses.put("Gas", gasTotal);
        expenses.put("Entertainment", entTotal);

        System.out.println("\nTotal Spent:");
        for(Map.Entry<String, Double> entry : expenses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        System.out.println("\n\nHashMap Route to store keywords");
        ArrayList<String> grocery = new ArrayList<>(List.of("market", "grocery"));
        ArrayList<String> gas = new ArrayList<>(List.of("gas", "fuel"));
        ArrayList<String> entertainment = new ArrayList<>(List.of("hbo", "steam", "game", "netflix", "spotify"));
        ArrayList<String> health = new ArrayList<>(List.of("pharmacy", "clinic", "hospital", "fitness"));
        ArrayList<String> bills = new ArrayList<>(List.of("water", "electricity", "bill"));
        ArrayList<String> restaurants = new ArrayList<>(List.of("taco", "tacos", "chipotle", "restaurant"));
        ArrayList<String> coffee = new ArrayList<>(List.of("coffee", "cafe", "espresso"));
        ArrayList<String> shopping = new ArrayList<>(List.of("amazon", "target"));

        HashMap<String, ArrayList<String>> categoryKeywords = new HashMap<>();
        categoryKeywords.put("Grocery", grocery);
        categoryKeywords.put("Gas", gas);
        categoryKeywords.put("Health", health);
        categoryKeywords.put("Bills", bills);
        categoryKeywords.put("Restaurants", restaurants);
        categoryKeywords.put("Coffee", coffee);
        categoryKeywords.put("Shopping", shopping);
        categoryKeywords.put("Entertainment", entertainment);

        for(Transaction transaction : transactionsList) {
            String descriptLower = transaction.getDescription().toLowerCase();
            for(Map.Entry<String, ArrayList<String>> entry : categoryKeywords.entrySet()) {
                for(String keyword : entry.getValue()) {
                    if(descriptLower.contains(keyword)) {
                        transaction.setCategory(entry.getKey());
                    }
                }
            }
        }

        System.out.println("\nTransactions with Category: ");
        for(Transaction transaction : transactionsList) {
            System.out.println(transaction.getDate() + "     "
                    + transaction.getDescription() + "     "
                    + transaction.getAmount() + "     "
                    + transaction.getCategory());
        }

        // displays category and amount repetitively, i don't need this. just for debugging
//        for(Map.Entry<String, ArrayList<String>> entry : categoryKeywords.entrySet()) {
//            for(Transaction transaction : transactionsList) {
//                if(entry.getKey().equalsIgnoreCase(transaction.getCategory())) {
//                    System.out.println(entry.getKey() + "   " + transaction.getAmount());
//                }
//            }
//        }

        //Double total2 = 0.00;
        Double currentTotal = 0.00;

        // trying to do totals per cateogry in more programatic way
        HashMap<String, Double> expenses2 = new HashMap<>();
        for(Transaction transaction : transactionsList) {
            String category = transaction.getCategory();
            Double amount = transaction.getAmount();
            Double total = expenses2.get(category);

            if(expenses2.containsKey(category)) {
                total += amount;
                expenses2.put(category, total);
            } else {
                expenses2.put(category, amount);
            }
        }

        System.out.println("\n\nTOTAL HASHMAP");
        for (Map.Entry<String, Double> entry : expenses2.entrySet()) {
            System.out.println(entry.getKey() + "     " + entry.getValue());
        }





    }
}