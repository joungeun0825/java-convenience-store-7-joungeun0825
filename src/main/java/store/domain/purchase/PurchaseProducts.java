package store.domain.purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseProducts {
    List<PurchaseProduct> purchaseProducts;

    public PurchaseProducts(){
        this.purchaseProducts = new ArrayList<>();
    }

    public void add(PurchaseProduct purchaseProduct) {
        this.purchaseProducts.add(purchaseProduct);
    }

    public List<PurchaseProduct> getProducts() {
        return purchaseProducts;
    }
}
