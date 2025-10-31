import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportGenerator {

    private ArrayList<Transaction> transactions;
    private HashMap<String, BigDecimal> expenses = new HashMap<>();

    public ReportGenerator(ArrayList<Transaction> transactionList) {
        this.transactions = transactionList;
    }

    public HashMap<String, BigDecimal> getExpensesByCategory() {
        HashMap<String, BigDecimal> expenses = new HashMap<>();
        for(Transaction transaction : transactions) {
            String category = transaction.getCategory();
            BigDecimal amount = transaction.getAmount();
            BigDecimal total = expenses.getOrDefault(category, BigDecimal.ZERO);

            total = total.add(amount);
            expenses.put(category, total);
        }

        return expenses;
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return date.format(dateFormatter);
    }

    public String formatMoney(BigDecimal amount) {
        String formattedMoney = "";

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            // Negative: get absolute value and format as -$XX.XX
            BigDecimal absoluteValue = amount.abs();
            formattedMoney = String.format("-$%.2f", absoluteValue);
        } else {
            // Positive or zero: format as $XX.XX
            formattedMoney =  String.format("$%.2f", amount);
        }

        return formattedMoney;
    }

    public void printToConsole() {;

        String headings = "Date           " + "Description             " + "Amount      " + "Category         ";
        String money = "";
        String date = "";

        System.out.println("\nTRANSACTION REPORT");
        System.out.println("===================");
        System.out.println(headings);
        for(Transaction transaction : transactions) {
            date = formatDate(transaction.getDate());
            money = formatMoney(transaction.getAmount());
            System.out.println(date + "     " +
                                transaction.getDescription() + "     " +
                                money + "     " +
                                transaction.getCategory());
        }

        System.out.println("\nSPENDING BY CATEGORY");
        System.out.println("=====================");

        HashMap<String, BigDecimal> displayExpenses = getExpensesByCategory();
        for(Map.Entry<String, BigDecimal> entry : displayExpenses.entrySet()) {
            System.out.println(entry.getKey() + ": " + formatMoney(entry.getValue()));
        }

        String formattedGrandTotal = "";
        BigDecimal grandTotal = BigDecimal.ZERO;
        for(Transaction transaction : transactions) {
            grandTotal = grandTotal.add(transaction.getAmount());
        }

        formattedGrandTotal = formatMoney(grandTotal);

        System.out.println("\nGRAND TOTAL: " + formattedGrandTotal);
    }
}
