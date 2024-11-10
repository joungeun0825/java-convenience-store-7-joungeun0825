package store.domain.product;

public class Product {
    private static final String TO_STRING_FORMAT = "- %s %s원 %d개";
    private static final String TO_STRING_FORMAT_EMPTY_STOCK = "- %s %s원 재고 없음";

    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity += newQuantity;
    }

    public void updatePurchase(int newQuantity) {
        this.quantity -= newQuantity;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        if(this.quantity > 0){
            return String.format(TO_STRING_FORMAT, name, String.format("%,d", price), quantity);
        }
        return String.format(TO_STRING_FORMAT_EMPTY_STOCK, name, String.format("%,d", price));
    }
}
