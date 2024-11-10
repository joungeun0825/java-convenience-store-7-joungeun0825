package store.domain.product;

import java.util.EnumMap;

public class ProductManager {
    private static final EnumMap<ProductType, Product> productMap = new EnumMap<>(ProductType.class);
    private static final EnumMap<ProductType, PromotionProduct> promotionProductMap = new EnumMap<>(ProductType.class);

    public static boolean existValidPromotion(String promotionProductName){
        PromotionProduct promotionProduct = getPromotionProduct(promotionProductName);
        return (promotionProduct != null && promotionProduct.isActivePromotion() && promotionProduct.isStockExist());
    }

    public static boolean isPurchaseQuantityAvailable(String name, int quantity) {
        Product product = getProduct(name);
        PromotionProduct promotionProduct = getPromotionProduct(name);

        int availableQuantity = product.getQuantity();
        if (existValidPromotion(name) && promotionProduct != null) {
            availableQuantity += promotionProduct.getQuantity();
        }

        return availableQuantity >= quantity;
    }

    public static void putProduct(String productName, Product product) {
        productMap.put(ProductType.valueOf(productName), product);
    }

    public static void putPromotionProduct(String promotionProductName, PromotionProduct product){
            promotionProductMap.put(ProductType.valueOf(promotionProductName), product);
    }

    public static Product getProduct(String productName) {
        return productMap.get(ProductType.valueOf(productName));
    }

    public static PromotionProduct getPromotionProduct(String promotionProductName) {
        return promotionProductMap.get(ProductType.valueOf(promotionProductName));
    }
}
