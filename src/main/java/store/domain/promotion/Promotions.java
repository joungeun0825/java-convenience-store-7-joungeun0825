package store.domain.promotion;

import java.util.HashMap;
import java.util.Map;

public class Promotions {
    Map<String, Promotion> promotions;

    public Promotions() {
        this.promotions = new HashMap<>();
    }

    public void add(Promotion promotion) {
        promotions.put(promotion.getName(), promotion);
    }

    public int getBuyByPromotion(String promotion){
        return promotions.get(promotion).getBuy();
    }

    public int getGetByPromotion(String promotion){
        return promotions.get(promotion).getGet();
    }

    public String getStartDateByPromotion(String promotion){
        return promotions.get(promotion).getStartDate();
    }

    public String getEndDateByPromotion(String promotion){
        return promotions.get(promotion).getEndDate();
    }
}
