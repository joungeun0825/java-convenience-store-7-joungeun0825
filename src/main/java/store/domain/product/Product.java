package store.domain.product;

import store.domain.promotion.Promotion;

public class Product {
    private static final String TO_STRING_FORMAT = "- %s %s원 %d개";
    private static final String TO_STRING_FORMAT_EMPTY_STOCK = "- %s %s원 재고 없음";

    private String name;
    private int price;
    private int quantity;
    private PromotionProduct promotionProduct;

    public Product(String name) {
        this.name = name;
        this.price = 0;
        this.quantity = 0;
    }

    public void updatePrice(int newPrice) {
        this.price = newPrice;
    }

    public void updateRegularProduct(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public void updatePromotionProduct(int price, int promotionQuantity, Promotion promotion) {
        this.promotionProduct = new PromotionProduct(name, price, promotionQuantity, promotion);
    }

    public void updateQuantity(int newQuantity) {
        this.quantity += newQuantity;
    }

    public void updatePurchase(int newQuantity) {
        this.quantity -= newQuantity;
    }

    public boolean existValidPromotion() {
        return (promotionProduct != null && promotionProduct.isActivePromotion() && promotionProduct.isStockExist());
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

    @Override
    public String toString() {
        if(this.quantity > 0){
            return String.format(TO_STRING_FORMAT, name, String.format("%,d", price), quantity);
        }
        return String.format(TO_STRING_FORMAT_EMPTY_STOCK, name, String.format("%,d", price));
    }
}
