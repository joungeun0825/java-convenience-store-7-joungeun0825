package store.domain.product;

public class PromotionProduct {
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public PromotionProduct(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public boolean hasSufficientPromotionStock(int discountQuantity){
        return quantity >= discountQuantity;
    }

    public void decreasePromotionStock(int discountQuantity){
        this.quantity -= quantity;
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

    public String getPromotion() {
        return this.promotion;
    }

}
