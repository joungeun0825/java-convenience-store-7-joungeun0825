package store.domain.promotion;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Promotions {
    private static Map<String, Promotion> promotions = new HashMap<>();

    public static void add(Promotion promotion) {
        promotions.put(promotion.getName(), promotion);
    }

    public static Promotion getPromotionByName(String name) {
        return promotions.get(name);
    }

    public static Map<String, Promotion> getPromotions() {
        return new HashMap<>(promotions);
    }

    public static int getBuyByPromotion(String promotion) {
        return promotions.get(promotion).getBuy();
    }

    public static int getGetByPromotion(String promotion) {
        return promotions.get(promotion).getGet();
    }

    public static LocalDateTime getStartDateByPromotion(String promotion){
        return promotions.get(promotion).getStartDate();
    }

    public static LocalDateTime getEndDateByPromotion(String promotion){
        return promotions.get(promotion).getEndDate();
    }
}
