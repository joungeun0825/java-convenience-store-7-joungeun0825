package store.domain.product;

import java.util.HashMap;
import java.util.Map;

public class PromotionProducts {
    private Map<String, PromotionProduct> promotionProducts;

    public PromotionProducts() {
        this.promotionProducts = new HashMap<>();
    }

    public void add(PromotionProduct promotionProduct) {
        promotionProducts.put(promotionProduct.getName(), promotionProduct);
    }

    public int getPriceByName(String name) {
        return promotionProducts.get(name).getPrice();
    }

    public int getQuantityByName(String name) {
        return promotionProducts.get(name).getQuantity();
    }

    public String getPromotionByName(String name) {
        return promotionProducts.get(name).getPromotion();
    }
}
