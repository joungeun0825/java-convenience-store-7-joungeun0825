package store.domain.product;

import java.util.HashMap;
import java.util.Map;

public class PromotionProducts {
    private static Map<String, PromotionProduct> promotionProducts = new HashMap<>();

    public static void add(PromotionProduct promotionProduct) {
        promotionProducts.put(promotionProduct.getName(), promotionProduct);
    }

    public static Map<String, PromotionProduct> getPromotionProducts() {
        return new HashMap<>(promotionProducts);
    }

    public static int getPriceByName(String name) {
        return promotionProducts.get(name).getPrice();
    }

    public static int getQuantityByName(String name) {
        return promotionProducts.get(name).getQuantity();
    }

    public static String getPromotionByName(String name) {
        return promotionProducts.get(name).getPromotion();
    }
}
