import java.util.Date;

public class Transaction {

    private String date;
    // TODO: Use LocalDate later once the String date is working
    private String description;
    private double amount;
    // TODO: turn amount to BigDecimal later, double type for now

    public Transaction(String date, String description, double amount) {
        this.date = date;
        this.description = description;
        this. amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
