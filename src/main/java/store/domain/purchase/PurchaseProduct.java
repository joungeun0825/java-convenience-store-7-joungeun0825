package store.domain.purchase;

import store.domain.discount.PromotionDiscount;
import store.domain.product.ProductRegistry;
import store.domain.product.ProductType;

public class PurchaseProduct {
    private String name;
    private int quantity;
    private int price;
    private PromotionDiscount promotionDiscount;

    public PurchaseProduct(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = calcPrice();
    }

    public int calcPrice() {
        return ProductRegistry.getProduct(this.name).getPrice() * this.quantity;
    }

    public void updatePurchaseWithQuantity(int newQuantity) {
        this.quantity = newQuantity;
        updatePriceWithQuantity(newQuantity);
    }

    private void updatePriceWithQuantity(int newQuantity) {
        this.price = ProductRegistry.getProduct(this.name).getPrice() * newQuantity;
    }

    public void applyPromotionDiscount(PromotionDiscount promotionDiscount) {
        if (promotionDiscount.getPrice() > 0) {
            this.promotionDiscount = promotionDiscount;
        }
    }

    public int checkPromotionAndGetPrice() {
        if (this.promotionDiscount == null) {
            return this.price;
        }
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice() {
        return this.price;
    }
}
