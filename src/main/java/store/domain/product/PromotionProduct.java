package store.domain.product;

import store.domain.promotion.Promotion;

public class PromotionProduct {
    private String name;
    private int price;
    private int quantity;
    private Promotion promotion;

    public PromotionProduct(String name, int price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public int calculateRealDiscountQuantity() {
        return promotion.calculateRealDiscountQuantity(quantity);
    }

    public void decreasePromotionStock(int discountQuantity){
        this.quantity -= quantity;
    }

    public boolean isActivePromotion(){
        return this.promotion.isWithinPromotionPeriod();
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

    public Promotion getPromotion(){
        return this.promotion;
    }

}
