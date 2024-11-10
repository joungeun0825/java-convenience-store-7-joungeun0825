package store.service;

import store.domain.product.ProductRegistry;
import store.domain.product.ProductType;
import store.domain.product.PromotionProduct;
import store.domain.promotion.Promotion;
import store.domain.purchase.PurchaseProduct;
import store.domain.purchase.PurchaseProducts;
import store.domain.product.Product;
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
        Product product = ProductRegistry.getProduct(purchaseProduct.getName());
        PromotionProduct promotionProduct = ProductRegistry.getPromotionProduct(purchaseProduct.getName());
        if (ProductRegistry.existValidPromotion(purchaseProduct.getName())) {
            PromotionDiscountService.applyPromotionDiscount(promotionProduct, purchaseProduct);
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
