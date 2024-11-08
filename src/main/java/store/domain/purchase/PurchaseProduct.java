package store.domain.purchase;

import store.domain.product.Products;

public class PurchaseProduct {
    private String name;
    private int quantity;
    private int price;

    public PurchaseProduct(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
        this.price = calcPrice();
    }

    public int calcPrice(){
        return Products.getPriceByName(this.name)*this.quantity;
    }

    public void updatePurchaseWithQuantity(int newQuantity){
        this.quantity = newQuantity;
        updatePriceWithQuantity(newQuantity);
    }

    private void updatePriceWithQuantity(int newQuantity){
        this.price = Products.getPriceByName(this.name)*newQuantity;
    }

    public String getName(){
        return this.name;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public int getPrice(){
        return this.price;
    }
}
