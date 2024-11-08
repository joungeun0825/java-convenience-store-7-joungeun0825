package store.domain.product;

public class Product {
    private String name;
    private int price;
    private int quantity;

    public Product() {
    }

    public void updateProduct(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public boolean isQuantityLeft(int requiredQuantity){
        return this.quantity >= requiredQuantity;
    }

    public void subtractQuantity(int requiredQuantity){
        this.quantity -= requiredQuantity;
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
}
