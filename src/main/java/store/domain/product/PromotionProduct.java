package store.domain.product;

import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;

public class PromotionProduct {
    private static final String TO_STRING_FORMAT = "- %s %s원 %d개 %s";
    private static final String TO_STRING_FORMAT_EMPTY_STOCK = "- %s %s원 재고 없음 %s";

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

    public boolean canApplyPromotion(Promotion promotion, PurchaseProduct purchaseProduct) {
        int promotionCycle = promotion.getBuy() + promotion.getGet();
        return canGiveMore(purchaseProduct.getQuantity() + promotion.getGet()) &&
                (purchaseProduct.getQuantity() % promotionCycle == promotion.getBuy());
    }

    private boolean canGiveMore(int newQuantity) {
        return this.quantity >= newQuantity;
    }

    public int calculateRealDiscountQuantity() {
        return promotion.calculateRealDiscountQuantity(quantity);
    }

    public void decreasePromotionStock(int discountQuantity) {
        this.quantity -= discountQuantity;
        if (this.quantity < 0) {
            ProductManager.getProduct(this.name).adjustMainProductStock(this.quantity);
            this.quantity = 0;
        }
    }

    public boolean isStockExist() {
        return this.quantity > 0;
    }

    public boolean isActivePromotion() {
        return this.promotion.isWithinPromotionPeriod();
    }

    public int calcPromotionDiscount(int promotionQuantity){
        return this.price * promotionQuantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getPrice(){
        return this.price;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    @Override
    public String toString() {
        if (this.quantity > 0) {
            return String.format(TO_STRING_FORMAT, name, String.format("%,d", price), quantity, promotion.getName());
        }
        return String.format(TO_STRING_FORMAT_EMPTY_STOCK, name, String.format("%,d", price), promotion.getName());
    }

}
