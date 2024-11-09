package store.service;

import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;
import store.domain.product.Product;
import store.domain.product.TotalProduct;
import store.domain.receipt.Receipt;
import store.view.AskCustomerInputView;

public class PurchaseService {
    public static void purchaseProducts(PurchaseProducts purchaseProducts) {
        for (PurchaseProduct purchaseProduct : purchaseProducts.getProducts()) {
            purchase(purchaseProduct);
            Receipt.addPurchaseProduct(purchaseProduct);
        }
        applyMembershipDiscount(purchaseProducts);
    }

    private static void purchase(PurchaseProduct purchaseProduct) {
        Product product = TotalProduct.valueOf(purchaseProduct.getName()).getProduct();
        if (product.existValidPromotion()) {
            PromotionDiscountService.applyPromotionDiscount(product.getPromotionProduct(), purchaseProduct);
            return;
        }
        product.updatePurchase(purchaseProduct.getQuantity());
    }

    private static void applyMembershipDiscount(PurchaseProducts purchaseProducts) {
        if (AskCustomerInputView.askCustomerToApplyMembership()) {
            MembershipDiscountService.applyMembershipDiscount(purchaseProducts);
        }
    }
}
