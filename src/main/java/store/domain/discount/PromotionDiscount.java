package store.domain.discount;

import store.domain.product.ProductRegistry;

public class PromotionDiscount {
    private String productName;
    private int quantity;
    private int price;

    public PromotionDiscount(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = calcPrice(quantity);
    }

    public PromotionDiscount updatePromotionDiscountWithQuantity(int newQuantity) {
        this.quantity = newQuantity;
        this.price = calcPrice(newQuantity);
        return this;
    }

    public int calcPrice(int quantity) {
        return ProductRegistry.getPromotionProduct(this.productName).getPrice() * quantity;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }
}
