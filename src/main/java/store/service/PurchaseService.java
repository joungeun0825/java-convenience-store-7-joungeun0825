package store.service;

import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;
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
            PromotionDiscountService.applyPromotionDiscount(product.getPromotionProduct(), purchaseProduct);
            return;
        }
        product.updatePurchase(purchaseProduct.getQuantity());
        Receipt.addPurchaseProduct(purchaseProduct);
    }
}
