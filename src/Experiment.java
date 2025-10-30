import java.math.BigDecimal;

public class Experiment {

    public static void main(String[] args) {
        double money1 = 0.1 + 0.2;
        System.out.println("Using double: " + money1);

        BigDecimal money2 = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("Using BigDecimal: " + money2);

        /*
        * Floating points errors: BAD!!
        *
        * BigDecimal stores numbers as exact decimal values, NOT binary approximations
        * */
    }
}
