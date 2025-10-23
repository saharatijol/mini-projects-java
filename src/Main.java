import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                double amount = Double.parseDouble(arr[2]);
                transactionsList.add(new Transaction(arr[0], arr[1], amount));
            }

            for(Transaction transaction : transactionsList) {
                System.out.println(transaction.getDate() + "     "
                                    + transaction.getDescription() + "     "
                                    + transaction.getAmount());
            }


        } catch (FileNotFoundException e) {
            System.err.println("ERROR: " + FILE + " not found in data/ folder");

        } catch (IOException e) {
            System.err.println("ERROR: Could not read file - " + e.getMessage());
            e.printStackTrace();
        }
    }
}