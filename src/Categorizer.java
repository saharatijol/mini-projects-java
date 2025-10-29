import java.util.*;

public class Categorizer {

    private HashMap<String, ArrayList<String>> categoryKeywords = new HashMap<>();
    private HashMap<String, String> keywordCategoryMap = new HashMap<>();

    //constructor
    public Categorizer() {
        ArrayList<String> grocery = new ArrayList<>(List.of("market", "grocery"));
        ArrayList<String> gas = new ArrayList<>(List.of("gas", "fuel"));
        ArrayList<String> entertainment = new ArrayList<>(List.of("hbo", "steam", "game", "netflix", "spotify"));
        ArrayList<String> health = new ArrayList<>(List.of("pharmacy", "clinic", "hospital", "fitness"));
        ArrayList<String> bills = new ArrayList<>(List.of("water", "electricity", "bill"));
        ArrayList<String> restaurants = new ArrayList<>(List.of("taco", "tacos", "chipotle", "restaurant"));
        ArrayList<String> coffee = new ArrayList<>(List.of("coffee", "cafe", "espresso"));
        ArrayList<String> shopping = new ArrayList<>(List.of("amazon", "target"));

        this.categoryKeywords.put("Grocery", grocery);
        this.categoryKeywords.put("Gas", gas);
        this.categoryKeywords.put("Health", health);
        this.categoryKeywords.put("Bills", bills);
        this.categoryKeywords.put("Restaurants", restaurants);
        this.categoryKeywords.put("Coffee", coffee);
        this.categoryKeywords.put("Shopping", shopping);
        this.categoryKeywords.put("Entertainment", entertainment);

        this.keywordCategoryMap = reverseMap();
    }

    public void categorize(Transaction transaction) {
        Optional<String> category = findCategory(transaction.getDescription());
        if(category.isPresent()) {
            transaction.setCategory(category.get());
        }
    }

    public HashMap<String, String> reverseMap() {
        HashMap<String, String> keywordToCategory = new HashMap<>();
        for(Map.Entry<String, ArrayList<String>> entry : categoryKeywords.entrySet()) {
            for(String keyword : entry.getValue()) {
                keywordToCategory.put(keyword, entry.getKey());
            }
        }
        return keywordToCategory;
    }

    private Optional<String> findCategory(String category) {
        String description = category.toLowerCase();
        String[] words = description.split(" ");
        for(String word : words) {
            if(keywordCategoryMap.containsKey(word)) {
                return Optional.of(keywordCategoryMap.get(word));
            }
        }
        return Optional.empty();
    }
}
