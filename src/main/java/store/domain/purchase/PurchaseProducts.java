package store.domain.purchase;

import java.util.HashMap;
import java.util.Map;

public class PurchaseProducts {
    Map<String, Integer> purchaseProducts;

    public PurchaseProducts(){
        this.purchaseProducts = new HashMap<>();
    }

    public void add(String productName, int quantity) {
        int originalQuantity = purchaseProducts.getOrDefault(productName, 0);
        purchaseProducts.put(productName, originalQuantity + quantity);
    }

}
