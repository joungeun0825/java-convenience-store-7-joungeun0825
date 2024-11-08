package store.domain.purchase;

import store.domain.discount.PromotionDiscountManager;
import store.domain.product.Product;
import store.domain.product.TotalProduct;
import store.domain.receipt.Receipt;

public class PurchaseService {
    public static void purchaseProducts(PurchaseProducts purchaseProducts) {
        for (PurchaseProduct purchaseProduct : purchaseProducts.getProducts()) {
            purchase(purchaseProduct);
        }
    }

    private static void purchase(PurchaseProduct purchaseProduct) {
        Product product = TotalProduct.valueOf(purchaseProduct.getName()).getProduct();
        if (product.existValidPromotion()) {
            PromotionDiscountManager.applyPromotionDiscount(product.getPromotionProduct(), purchaseProduct);
        }
        Receipt.addPurchaseProduct(purchaseProduct);
    }
}
