package store.domain.product;

import store.domain.promotion.Promotion;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private PromotionProduct promotionProduct;

    public Product(String name) {
        this.name = name;
    }

    public void updateRegularProduct(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void updatePromotionProduct(int price, int promotionQuantity, Promotion promotion) {
        this.promotionProduct = new PromotionProduct(name, price, promotionQuantity, promotion);
    }

    public boolean existValidPromotion() {
        return (promotionProduct != null && promotionProduct.isActivePromotion());
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public PromotionProduct getPromotionProduct() {
        return this.promotionProduct;
    }
}
