import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private LocalDate date;
    private String description;
    private BigDecimal amount;
    // TODO: turn amount to BigDecimal later, double type for now
    private String category = "Uncategorized";

    public Transaction(LocalDate date, String description, BigDecimal amount) {
        this.date = date;
        this.description = description;
        this. amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
