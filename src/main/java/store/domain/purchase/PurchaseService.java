package store.domain.purchase;

import store.domain.discount.PromotionDiscountManager;
import store.domain.receipt.Receipt;

public class PurchaseService {
    public static void purchaseProducts(PurchaseProducts purchaseProducts){
        for(PurchaseProduct purchaseProduct : purchaseProducts.getProducts()){
            PromotionDiscountManager.applyPromotionDiscount(purchaseProduct);
            Receipt.addPurchaseProduct(purchaseProduct);
        }
    }
}
