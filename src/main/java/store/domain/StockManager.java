package store.domain;

import store.domain.product.Product;

public class StockManager {
    public boolean checkStockLeft(Product product, int requiredQuantity) {
        return product.isQuantityLeft(requiredQuantity);
    }

    public void subtractStock(Product product, int requiredQuantity){
        product.subtractQuantity(requiredQuantity);
    }

    public int getStockQuantity(Product product){
        return product.getQuantity();
    }
}
